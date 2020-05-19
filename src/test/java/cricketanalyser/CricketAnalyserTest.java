package cricketanalyser;

import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyserTest {

    private static final String IPL2019_MOST_RUNS_CSV_FILE_PATH = "./src/test/java/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL2019_MOST_RUNS_WRONG_TYPE_FILE_PATH = "./src/test/java/resources/IPL2019FactsheetMostRuns.txt";
    private static final String IPL2019_MOST_RUNS_CSV_FILE_PATH_WITH_INCORRECT_DELIMITER = "./src/test/java/resources/IPL2019FactsheetMostRunsIncorrectDelimiter.csv";
    private static final String IPL2019_MOST_RUNS_CSV_FILE_PATH_WITH_INCORRECT_HEADER = "./src/test/java/resources/IPL2019FactsheetMostRunsIncorrectHeader.csv";

    @Test
    public void givenIPL2019MostRunsCSVFile_ReturnsCorrectRecords() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            int numOfPlayers = cricketAnalyser.loadIPLData(IPL2019_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfPlayers);
        } catch (CricketAnalyserException cricketAnalyserException) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WithWrongFileType_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLData(IPL2019_MOST_RUNS_WRONG_TYPE_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsData_WithWrongFilePath_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLData(IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsData_WithIncorrectDelimiter_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLData(IPL2019_MOST_RUNS_CSV_FILE_PATH_WITH_INCORRECT_DELIMITER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsData_WithIncorrectHeader_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadIPLData(IPL2019_MOST_RUNS_CSV_FILE_PATH_WITH_INCORRECT_HEADER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }
}
