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
                Iterator<BatsMen> csvFileIterator = csvBuilder.getCSVFileIterator(reader, BatsMen.class);
                Iterable<BatsMen> csvIterable = () -> csvFileIterator;
                StreamSupport.stream(csvIterable.spliterator(), false).
                        forEach(iplDataCsv -> cricketDataMap.put(iplDataCsv.player, new CricketDataDAO(iplDataCsv)));
            } else{
                Iterator<Bowlers> csvFileIterator = csvBuilder.getCSVFileIterator(reader, Bowlers.class);
                Iterable<Bowlers> csvIterable = () -> csvFileIterator;
                StreamSupport.stream(csvIterable.spliterator(), false).
                        forEach(iplDataCsv -> cricketDataMap.put(iplDataCsv.player, new CricketDataDAO(iplDataCsv)));
            }
            return cricketDataMap;
        } catch (IOException | CSVBuilderException | RuntimeException ioException) {
            throw new CricketAnalyserException(ioException.getMessage(),
                    CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }
}
