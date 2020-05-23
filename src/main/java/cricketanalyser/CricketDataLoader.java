package cricketanalyser;

import com.csvloader.CSVBuilderException;
import com.csvloader.CSVBuilderFactory;
import com.csvloader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CricketDataLoader {

    public CricketDataLoader() {
    }

    Map<String, CricketDataDAO> cricketDataMap = null;

    public Map<String, CricketDataDAO> loadData(CricketAnalyser.CricketData data, String csvFilePath) throws CricketAnalyserException {
        Map<String, CricketDataDAO> cricketDataMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            if (data.equals(CricketAnalyser.CricketData.BATSMEN)) {
                Iterator<BatsMen> csvFileIterator = csvBuilder.getCSVFileIterator(reader, BatsMen.class);
                Iterable<BatsMen> csvIterable = () -> csvFileIterator;
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(BatsMen.class::cast)
                        .forEach(censusCSV -> cricketDataMap.put(censusCSV.player, new CricketDataDAO(censusCSV)));
            } else if (data.equals(CricketAnalyser.CricketData.BOWLERS)) {
                Iterator<Bowlers> csvFileIterator = csvBuilder.getCSVFileIterator(reader, Bowlers.class);
                Iterable<Bowlers> csvIterable = () -> csvFileIterator;
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(Bowlers.class::cast)
                        .forEach(censusCSV -> cricketDataMap.put(censusCSV.player, new CricketDataDAO(censusCSV)));
            }
            return cricketDataMap;
        } catch (IOException | RuntimeException | CSVBuilderException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.FILE_PROBLEM);
        }
    }
}
