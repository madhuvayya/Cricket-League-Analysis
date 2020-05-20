package cricketanalyser;

import com.csvloader.CSVBuilderException;
import com.csvloader.CSVBuilderFactory;
import com.csvloader.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketAnalyser {

    Map<String, IPLDataDAO> iplDataMap = null;

    public Map<String, IPLDataDAO> loadIPLData(String CsvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPL2019MostRunsCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IPL2019MostRunsCSV.class);
            Iterable<IPL2019MostRunsCSV> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(),false).
                    forEach(iplDataCsv -> iplDataMap.put(iplDataCsv.player,new IPLDataDAO(iplDataCsv)));
            return this.iplDataMap;
        } catch (IOException ioException) {
            throw new CricketAnalyserException(ioException.getMessage(),
                    CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (CSVBuilderException csvBuilderException) {
            throw new CricketAnalyserException(csvBuilderException.getMessage(),
                    CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException runtimeException) {
            throw new CricketAnalyserException(runtimeException.getMessage(),
                    CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }

    public String getSortedIPLDataAccordingToBattingAverages() throws CricketAnalyserException {
        Comparator<IPLDataDAO> iplDataDAOComparator = Comparator.comparing(iplData -> iplData.average);
        return this.getSortedIPLData(iplDataDAOComparator);
    }

    public String getSortedIPLDataAccordingToStrikeRates() throws CricketAnalyserException {
        Comparator<IPLDataDAO> iplDataDAOComparator = Comparator.comparing(iplData -> iplData.strikeRate);
        return this.getSortedIPLData(iplDataDAOComparator);
    }

    private String getSortedIPLData(Comparator<IPLDataDAO> iplDataDAOComparator) throws CricketAnalyserException {
        if(iplDataMap == null || iplDataMap.size() ==0 ) {
            throw new CricketAnalyserException("No Census Data", CricketAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List sortedIPLData = iplDataMap.values().stream().
                sorted(iplDataDAOComparator).
                map(iplDataDAO -> iplDataDAO.getIPLDataDTO()).
                collect(Collectors.toList());
        return new Gson().toJson(sortedIPLData);
    }
}
