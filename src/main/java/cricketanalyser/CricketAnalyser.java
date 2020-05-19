package cricketanalyser;

import com.csvloader.CSVBuilderException;
import com.csvloader.CSVBuilderFactory;
import com.csvloader.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CricketAnalyser {

    List<IPL2019MostRunsCSV> ipl2019RunsSheetCSVList=null;

    public int loadIPLData(String CsvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CsvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            ipl2019RunsSheetCSVList=csvBuilder.getCSVFileList(reader, IPL2019MostRunsCSV.class);
            return ipl2019RunsSheetCSVList.size();
        } catch (IOException ioException){
            throw new CricketAnalyserException(ioException.getMessage(),
                    CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (CSVBuilderException csvBuilderException){
            throw new CricketAnalyserException(csvBuilderException.getMessage(),
                    CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException runtimeException){
            throw new CricketAnalyserException(runtimeException.getMessage(),
                    CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }

    }
}
