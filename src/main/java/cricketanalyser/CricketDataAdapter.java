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

public abstract class CricketDataAdapter {

    Map<String, CricketDataDAO> iplAnalysisMap = new HashMap<>();

    public abstract Map<String, CricketDataDAO> loadData(String ...csvFilePath) throws CricketAnalyserException;

    public <E> Map<String,CricketDataDAO> getCricketData(Class<E> iplDataCsvClass, String csvFilePath)
                                                                throws CricketAnalyserException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, iplDataCsvClass);
            Iterable<E> csvIterable = () -> csvFileIterator;
            if(iplDataCsvClass.getName().equals("cricketanalyser.BatsMen")) {
                StreamSupport.stream(csvIterable.spliterator(), false).
                        map(BatsMen.class::cast).
                        forEach(iplDataCsv -> iplAnalysisMap.put(iplDataCsv.player, new CricketDataDAO(iplDataCsv)));
            } else if(iplDataCsvClass.getName().equals("cricketanalyser.Bowlers")) {
                StreamSupport.stream(csvIterable.spliterator(), false).
                        map(Bowlers.class::cast).
                        forEach(iplDataCsv -> iplAnalysisMap.put(iplDataCsv.player, new CricketDataDAO(iplDataCsv)));
            }
            if(iplAnalysisMap.size() == 0)
                throw new CricketAnalyserException("NO_DATA",
                        CricketAnalyserException.ExceptionType.NO_DATA);
            return this.iplAnalysisMap;
        } catch (IOException | CSVBuilderException | RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.FILE_PROBLEM);
        }
    }
}
