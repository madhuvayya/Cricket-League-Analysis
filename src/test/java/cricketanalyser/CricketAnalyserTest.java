package cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyserTest {

    private static final String BATSMEN_DATA_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv" ;
    private static final String BATSMEN_DATA_WRONG_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String BATSMEN_DATA_WRONG_TYPE_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.txt";
    private static final String BATSMEN_DATA_PATH_WITH_INCORRECT_DELIMITER = "./src/test/resources/IPL2019FactsheetMostRunsIncorrectDelimiter.csv";
    private static final String BATSMEN_DATA_FILE_PATH_PATH_WITH_INCORRECT_HEADER = "./src/test/resources/IPL2019FactsheetMostRunsIncorrectHeader.csv";
    private static final String BOWLERS_DATA_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPL2019MostRunsCSVFile_whenDataLoaded_shouldReturnsCorrectRecords() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            int numOfPlayers = cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            Assert.assertEquals(100, numOfPlayers);
        } catch (CricketAnalyserException cricketAnalyserException) { }
    }

    @Test
    public void givenIPL2019MostWicketsCSVFile_whenDataLoaded_shouldReturnsCorrectRecords() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            int numOfPlayers = cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS,BOWLERS_DATA_FILE_PATH);
            Assert.assertEquals(99, numOfPlayers);
        } catch (CricketAnalyserException cricketAnalyserException) {
            cricketAnalyserException.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsData_whenWrongFilePath_shouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_WRONG_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsData_whenFileTypeIsWrong_shouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_WRONG_TYPE_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsData_withIncorrectDelimiter_shouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_PATH_WITH_INCORRECT_DELIMITER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPL2019MostRunsData_withIncorrectHeader_shouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_FILE_PATH_PATH_WITH_INCORRECT_HEADER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIPL2019MostRunsData_whenSortDataAccordingToAverages_shouldReturnTopAverageBatsMen() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAverages(CricketAnalyser.CricketData.BATSMEN);
            CricketDataDAO[] sorted = new Gson().fromJson(sortedMostRunsData, CricketDataDAO[].class);
            Assert.assertEquals("MS Dhoni",sorted[sorted.length-1].player);
            Assert.assertEquals("David Warner",sorted[sorted.length-2].player);
        } catch (CricketAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortDataAccordingToAverages_shouldReturnBatsManWithLeastAverage() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAverages(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("Ishant Sharma",sorted[0].player);
            Assert.assertEquals("Alzarri Joseph",sorted[2].player);
        } catch (CricketAnalyserException e){ }
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortDataAccordingToStrikeRates_shouldReturnTopStrikeRateBatsMen() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRates(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("Ishant Sharma",sorted[sorted.length-1].player);
            Assert.assertEquals("Andre Russell",sorted[sorted.length-2].player);
        } catch (CricketAnalyserException e){ }
    }


    @Test
    public void givenIPL2019MostRunsData_whenSortDataAccordingToStrikeRates_shouldReturnBatsMenWithLeastStrikeRate() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRates(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("Bhuvneshwar Kumar",sorted[0].player);
            Assert.assertEquals("Mitchell McClenaghan",sorted[1].player);
        } catch (CricketAnalyserException e){ }
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortDataAccordingToFours_shouldReturnBatsMenHitMostFours() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToFours(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("Shikhar Dhawan", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortDataAccordingToFours_shouldReturnBatsMenWhoHitLessFours() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToFours(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("Tim Southee", sorted[0].player);
            Assert.assertEquals("Kagiso Rabada", sorted[1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortDataAccordingToSixes_shouldReturnBatsMenHitMoreSixes() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToSixes(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("Andre Russell", sorted[sorted.length-1].player);
            Assert.assertEquals("Chris Gayle", sorted[sorted.length-2].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortDataAccordingToSixes_shouldReturnBatsMenHitLeastSixes() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToSixes(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("Kuldeep Yadav", sorted[0].player);
            Assert.assertEquals("Tim Southee", sorted[1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortDataAccordingToStrikeRateWithMaximumFoursAndSixes_shouldReturnPlayerInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToBestStrikeRateWithSixesAndFours(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("Andre Russell", sorted[sorted.length-1].player);
            Assert.assertEquals("Shakib Al Hasan", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortAccordingToGreatAveragesBestStrikeRates_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToGreatAveragesBestStrikeRates(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("MS Dhoni", sorted[sorted.length-1].player);
            Assert.assertEquals("Tim Southee", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_shenSortAccordingToRunsAndAverages_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToRunsAndAverages(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("David Warner", sorted[sorted.length-1].player);
            Assert.assertEquals("Tim Southee", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToAverages_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAverages(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedMostRunsData, Bowlers[].class);
            Assert.assertEquals("Mayank Markande", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_whenSortAccordingToStrikeRate_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRates(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedMostRunsData, Bowlers[].class);
            Assert.assertEquals("Mayank Markande", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_whenSortAccordingToEconomy_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToEconomy(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedMostRunsData, Bowlers[].class);
            Assert.assertEquals("Ben Cutting", sorted[sorted.length-1].player);
            Assert.assertEquals("Shivam Dube", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_whenSortAccordingToStrikeRateWithFiveAndFourWickets_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRateWithFiveAndFourWickets(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedMostRunsData, Bowlers[].class);
            Assert.assertEquals("Alzarri Joseph", sorted[sorted.length-1].player);
            Assert.assertEquals("Umesh Yadav", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_whenSortAccordingToAveragesAndStrikeRates_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAveragesAndStrikeRates(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedMostRunsData, Bowlers[].class);
            Assert.assertEquals("Mayank Markande", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToAveragesAndStrikeRates_ShouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedData = cricketAnalyser.getSortedCricketDataAccordingToAveragesAndStrikeRates(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedData, Bowlers[].class);
            Assert.assertEquals("Mayank Markande", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_whenSortAccordingToWicketsAndAverages_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedData = cricketAnalyser.getSortedCricketDataAccordingToWicketsAndAverages(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedData, Bowlers[].class);
            Assert.assertEquals("Mayank Markande", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIplBowlersAndBatsmenData_whenSortedAccordingToBestBattingAndBowlingAverages_shouldReturn()  {
        try{
            CricketAnalyser cricketAnalyser= new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS,BATSMEN_DATA_FILE_PATH);
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS,BOWLERS_DATA_FILE_PATH);
            String sortedData = cricketAnalyser.getSortedDataAccordingToBattingAndBowlingAverage(CricketAnalyser.CricketData.BATSMEN_BOWLERS);
            CricketDataDAO[] sorted =new Gson().fromJson(sortedData, CricketDataDAO[].class);
            Assert.assertEquals("Shreyas Iyer",sorted[sorted.length-1].player);
            Assert.assertEquals("Marcus Stoinis",sorted[0].player);
        } catch (CricketAnalyserException cricketAnalyserException) {
            cricketAnalyserException.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersAndBatsmenData_whenSortedAccordingToRunsAndWickets_shouldReturnPlayersInSorted()  {
        try{
            CricketAnalyser cricketAnalyser= new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS,BATSMEN_DATA_FILE_PATH);
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS,BOWLERS_DATA_FILE_PATH);
            String sortedData = cricketAnalyser.getSortedDataAccordingToBattingAndBowlingAverage(CricketAnalyser.CricketData.BATSMEN_BOWLERS);
            CricketDataDAO[] sorted =new Gson().fromJson(sortedData, CricketDataDAO[].class);
            Assert.assertEquals("Shreyas Iyer",sorted[sorted.length-1].player);
        } catch (CricketAnalyserException cricketAnalyserException) {
            cricketAnalyserException.printStackTrace();
        }
    }
}


