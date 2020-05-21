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

    public Map<String, CricketDataDAO> loadCricketData(String csvFilePath) throws CricketAnalyserException {
        Map<String, CricketDataDAO> cricketDataMap = new HashMap<>();
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLMostRunsCSV> csvIterator=icsvBuilder.getCSVFileIterator(reader, IPLMostRunsCSV.class);
            Iterable<IPLMostRunsCSV> csvIterable=()->csvIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .forEach(iplRunsCSV -> cricketDataMap.put(iplRunsCSV.player,new CricketDataDAO(iplRunsCSV)));
            return cricketDataMap;
        }catch(IOException e){
            throw new CricketAnalyserException("problem with CSV file",
                    CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM);

        }catch(CSVBuilderException e){
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.NOT_ABLE_TO_PARSE);
        }
    }
}
