package cricketanalyser;

import com.csvloader.CSVBuilderException;
import com.csvloader.CSVBuilderFactory;
import com.csvloader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CricketDataLoader {

    Map<String, CricketDataDAO> cricketDataMap = null;

    public Map<String, CricketDataDAO> loadBatsMenData(CricketAnalyser.CricketData data,String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            if(CricketAnalyser.CricketData.BATSMEN.equals(data)) {
                Iterator<IPLMostRunsCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IPLMostRunsCSV.class);
                Iterable<IPLMostRunsCSV> csvIterable = () -> csvFileIterator;
                StreamSupport.stream(csvIterable.spliterator(), false).
                        forEach(iplDataCsv -> cricketDataMap.put(iplDataCsv.player, new CricketDataDAO(iplDataCsv)));
            } else{
                Iterator<IPLMostRunsCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IPLMostRunsCSV.class);
                Iterable<IPLMostRunsCSV> csvIterable = () -> csvFileIterator;
                StreamSupport.stream(csvIterable.spliterator(), false).
                        forEach(iplDataCsv -> cricketDataMap.put(iplDataCsv.player, new CricketDataDAO(iplDataCsv)));
            }
            return cricketDataMap;
        } catch (IOException ioException) {
            throw new CricketAnalyserException(ioException.getMessage(),
                    CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (CSVBuilderException csvBuilderException) {
            throw new CricketAnalyserException(csvBuilderException.getMessage(),
                    CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException runtimeException) {
            throw new CricketAnalyserException(runtimeException.getMessage(),
                    CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }
}
