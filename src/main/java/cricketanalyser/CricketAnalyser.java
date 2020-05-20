package cricketanalyser;

import com.google.gson.Gson;

import com.csvloader.CSVBuilderException;
import com.csvloader.CSVBuilderFactory;
import com.csvloader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CricketAnalyser {

    List<IPL2019MostRunsCSV> ipl2019RunsSheetCSVList=null;

    Map<String, IPLDataDAO> ipl2019DataMap = null;

    public int loadIPL2019Data(String CsvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            ipl2019RunsSheetCSVList = csvBuilder.getCSVFileList(reader, IPL2019MostRunsCSV.class);
            return ipl2019RunsSheetCSVList.size();
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

    public String getSortedIPLDataAccordingToBattingAverages(String csvFilePath) throws CricketAnalyserException {
        Comparator<IPLDataDAO> iplDataDAOComparator = Comparator.comparing(iplData -> iplData.average);
        return this.getSortedIPLData(iplDataDAOComparator);
    }


    private String getSortedIPLData(Comparator<IPLDataDAO> iplDataDAOComparator) throws CricketAnalyserException {
        if(ipl2019DataMap == null || ipl2019DataMap.size() ==0 ) {
            throw new CricketAnalyserException("No Census Data", CricketAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List sortedIPLData = ipl2019DataMap.values().stream().
                sorted(iplDataDAOComparator).
                map(iplDataDAO -> iplDataDAO.getIPLDataDTO()).
                collect(Collectors.toList());
        return new Gson().toJson(sortedIPLData);
    }
}
