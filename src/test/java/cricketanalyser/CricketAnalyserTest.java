package cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

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
            Map<String, CricketDataDAO> numOfPlayers = cricketAnalyser.loadCricketData(IPL2019_MOST_RUNS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfPlayers);
        } catch (CricketAnalyserException cricketAnalyserException) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WithWrongFileType_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(IPL2019_MOST_RUNS_WRONG_TYPE_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsData_WithWrongFilePath_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsData_WithIncorrectDelimiter_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(IPL2019_MOST_RUNS_CSV_FILE_PATH_WITH_INCORRECT_DELIMITER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsData_WithIncorrectHeader_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(IPL2019_MOST_RUNS_CSV_FILE_PATH_WITH_INCORRECT_HEADER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToAverages_ShouldReturnTopAverageBatsMen() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToBattingAverages();
            IPL2019MostRunsCSV[] iplCSV = new Gson().fromJson(sortedMostRunsData, IPL2019MostRunsCSV[].class);
            Assert.assertEquals("MS Dhoni",iplCSV[1].player);
        } catch (CricketAnalyserException e){ }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToAverages_ShouldReturnSecondTopAverageBatsMen() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToBattingAverages();
            IPL2019MostRunsCSV[] iplCSV = new Gson().fromJson(sortedMostRunsData, IPL2019MostRunsCSV[].class);
            Assert.assertEquals("David Warner",iplCSV[2].player);
        } catch (CricketAnalyserException e){ }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToStrikeRates_ShouldReturnTopAverageBatsMen() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRates();
            IPL2019MostRunsCSV[] iplCSV = new Gson().fromJson(sortedMostRunsData, IPL2019MostRunsCSV[].class);
            Assert.assertEquals("Ishant Sharma",iplCSV[1].player);
        } catch (CricketAnalyserException e){ }
    }


    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToStrikeRates_ShouldReturnSecondTopAverageBatsMen() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(IPL2019_MOST_RUNS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRates();
            IPL2019MostRunsCSV[] iplCSV = new Gson().fromJson(sortedMostRunsData, IPL2019MostRunsCSV[].class);
            Assert.assertEquals("Andre Russell",iplCSV[2].player);
        } catch (CricketAnalyserException e){ }
    }
}
