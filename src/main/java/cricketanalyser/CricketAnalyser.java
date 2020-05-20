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

    Map<String, CricketDataDAO> cricketDataMap = null;

    public Map<String, CricketDataDAO> loadCricketData(String CsvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPL2019MostRunsCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IPL2019MostRunsCSV.class);
            Iterable<IPL2019MostRunsCSV> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(),false).
                    forEach(iplDataCsv -> cricketDataMap.put(iplDataCsv.player,new CricketDataDAO(iplDataCsv)));
            return this.cricketDataMap;
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

    public String getSortedCricketDataAccordingToBattingAverages() throws CricketAnalyserException {
        Comparator<CricketDataDAO> cricketDataDAOComparator = Comparator.comparing(cricketData -> cricketData.average);
        return this.getSortedIPLData(cricketDataDAOComparator);
    }

    public String getSortedCricketDataAccordingToStrikeRates() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplDataDAOComparator = Comparator.comparing(iplData -> iplData.strikeRate);
        return this.getSortedIPLData(iplDataDAOComparator);
    }

    private String getSortedIPLData(Comparator<CricketDataDAO> cricketDataDAOComparator) throws CricketAnalyserException {
        if(cricketDataMap == null || cricketDataMap.size() ==0 ) {
            throw new CricketAnalyserException("No Census Data", CricketAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List sortedCricketData = cricketDataMap.values().stream().
                sorted(cricketDataDAOComparator).
                map(cricketDataDAO -> cricketDataDAO.getCricketDataDTO()).
                collect(Collectors.toList());
        return new Gson().toJson(sortedCricketData);
    }
}
