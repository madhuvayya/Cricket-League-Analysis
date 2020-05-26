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
    public void givenIplBatsMenData_whenDataLoaded_shouldReturnsCorrectRecords() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            int numOfPlayers = cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            Assert.assertEquals(100, numOfPlayers);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersData_whenDataLoaded_shouldReturnsCorrectRecords() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BOWLERS);
            int numOfPlayers = cricketAnalyser.loadCricketData(BOWLERS_DATA_FILE_PATH);
            Assert.assertEquals(99, numOfPlayers);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBatsMenData_whenWrongFilePath_shouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_WRONG_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIplBatsMenData_whenFileTypeIsWrong_shouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_WRONG_TYPE_FILE_PATH);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIplBatsMenData_withIncorrectDelimiter_shouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_PATH_WITH_INCORRECT_DELIMITER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIplBatsMenData_withIncorrectHeader_shouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH_PATH_WITH_INCORRECT_HEADER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIplBatsMenData_whenSortDataAccordingToAverages_shouldReturnTopAverageBatsMen() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BATSMAN_AVERAGE);
            CricketDataDAO[] sorted = new Gson().fromJson(sortedMostRunsData, CricketDataDAO[].class);
            Assert.assertEquals("MS Dhoni",sorted[sorted.length-1].player);
            Assert.assertEquals("David Warner",sorted[sorted.length-2].player);
        } catch (CricketAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBatsMenData_whenSortDataAccordingToAverages_shouldReturnBatsManWithLeastAverage() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BATSMAN_AVERAGE);
            BatsMenData[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMenData[].class);
            Assert.assertEquals("Ishant Sharma",sorted[0].player);
            Assert.assertEquals("Alzarri Joseph",sorted[2].player);
        } catch (CricketAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBatsMenData_whenSortDataAccordingToStrikeRates_shouldReturnTopStrikeRateBatsMen() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BATSMAN_STRIKE);
            BatsMenData[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMenData[].class);
            Assert.assertEquals("Ishant Sharma",sorted[sorted.length-1].player);
            Assert.assertEquals("Andre Russell",sorted[sorted.length-2].player);
        } catch (CricketAnalyserException e){
            e.printStackTrace();
        }
    }


    @Test
    public void givenIplBatsMenData_whenSortDataAccordingToStrikeRates_shouldReturnBatsMenWithLeastStrikeRate() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BATSMAN_STRIKE);
            BatsMenData[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMenData[].class);
            Assert.assertEquals("Bhuvneshwar Kumar",sorted[0].player);
            Assert.assertEquals("Mitchell McClenaghan",sorted[1].player);
        } catch (CricketAnalyserException e){
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBatsMenData_whenSortDataAccordingToFours_shouldReturnBatsMenHitMostFours() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.FOURS);
            BatsMenData[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMenData[].class);
            Assert.assertEquals("Shikhar Dhawan", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBatsMenData_whenSortDataAccordingToFours_shouldReturnBatsMenWhoHitLessFours() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.FOURS);
            BatsMenData[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMenData[].class);
            Assert.assertEquals("Tim Southee", sorted[0].player);
            Assert.assertEquals("Kagiso Rabada", sorted[1].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBatsMenData_whenSortDataAccordingToSixes_shouldReturnBatsMenHitMoreSixes() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.SIXES);
            BatsMenData[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMenData[].class);
            Assert.assertEquals("Andre Russell", sorted[sorted.length-1].player);
            Assert.assertEquals("Chris Gayle", sorted[sorted.length-2].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBatsMenData_whenSortDataAccordingToSixes_shouldReturnBatsMenHitLeastSixes() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.SIXES);
            BatsMenData[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMenData[].class);
            Assert.assertEquals("Kuldeep Yadav", sorted[0].player);
            Assert.assertEquals("Tim Southee", sorted[1].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBatsMenData_whenSortDataAccordingToStrikeRateWithMaximumFoursAndSixes_shouldReturnPlayerInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.STRIKE_RATE_WITH_SIXES_AND_FOURS);
            BatsMenData[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMenData[].class);
            Assert.assertEquals("Andre Russell", sorted[sorted.length-1].player);
            Assert.assertEquals("Shakib Al Hasan", sorted[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBatsMenData_whenSortAccordingToGreatAveragesBestStrikeRates_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BATSMAN_AVERAGE_WITH_STRIKE);
            BatsMenData[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMenData[].class);
            Assert.assertEquals("MS Dhoni", sorted[sorted.length-1].player);
            Assert.assertEquals("Tim Southee", sorted[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBatsMenData_whenSortAccordingToRunsAndAverages_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BATSMAN_RUNS_WITH_AVERAGE);
            BatsMenData[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMenData[].class);
            Assert.assertEquals("David Warner", sorted[sorted.length-1].player);
            Assert.assertEquals("Tim Southee", sorted[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersData_WhenSortAccordingToAverages_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BOWLERS);
            cricketAnalyser.loadCricketData(BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BOWLER_AVERAGE);
            BowlersData[] sorted = new Gson().fromJson(sortedMostRunsData, BowlersData[].class);
            Assert.assertEquals("Krishnappa Gowtham", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersData_whenSortAccordingToStrikeRate_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BOWLERS);
            cricketAnalyser.loadCricketData(BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BOWLER_STRIKE_RATE);
            BowlersData[] sorted = new Gson().fromJson(sortedMostRunsData, BowlersData[].class);
            Assert.assertEquals("Krishnappa Gowtham", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersData_whenSortAccordingToEconomy_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BOWLERS);
            cricketAnalyser.loadCricketData(BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BOWLER_ECONOMY);
            BowlersData[] sorted = new Gson().fromJson(sortedMostRunsData, BowlersData[].class);
            Assert.assertEquals("Ben Cutting", sorted[sorted.length-1].player);
            Assert.assertEquals("Shivam Dube", sorted[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersData_whenSortAccordingToStrikeRateWithFiveAndFourWickets_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BOWLERS);
            cricketAnalyser.loadCricketData(BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BOWLER_STRIKE_WITH_FOUR_AND_FIVE_WICKETS);
            BowlersData[] sorted = new Gson().fromJson(sortedMostRunsData, BowlersData[].class);
            Assert.assertEquals("Lasith Malinga", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersData_whenSortAccordingToAveragesAndStrikeRates_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BOWLERS);
            cricketAnalyser.loadCricketData(BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BOWLER_AVERAGE_WITH_STRIKE);
            BowlersData[] sorted = new Gson().fromJson(sortedMostRunsData, BowlersData[].class);
            Assert.assertEquals("Krishnappa Gowtham", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersData_WhenSortAccordingToAveragesAndStrikeRates_ShouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BOWLERS);
            cricketAnalyser.loadCricketData(BOWLERS_DATA_FILE_PATH);
            String sortedData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BOWLER_AVERAGE_WITH_STRIKE);
            BowlersData[] sorted = new Gson().fromJson(sortedData, BowlersData[].class);
            Assert.assertEquals("Krishnappa Gowtham", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersData_whenSortAccordingToWicketsAndAverages_shouldReturnPlayersInSortedOrder() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.CricketData.BOWLERS);
            cricketAnalyser.loadCricketData(BOWLERS_DATA_FILE_PATH);
            String sortedData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BOWLER_WICKETS_WITH_AVERAGE);
            BowlersData[] sorted = new Gson().fromJson(sortedData, BowlersData[].class);
            Assert.assertEquals("Krishnappa Gowtham", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersAndBatsmenData_whenSortedAccordingToBestBattingAndBowlingAverages_shouldReturn()  {
        try{
            CricketAnalyser cricketAnalyser= new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN_BOWLERS);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH,BOWLERS_DATA_FILE_PATH);
            String sortedData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.BATTING_AVERAGE_WITH_BOWLING_AVERAGE);
            CricketDataDAO[] sorted =new Gson().fromJson(sortedData, CricketDataDAO[].class);
            Assert.assertEquals("Suresh Raina",sorted[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplBowlersAndBatsmenData_whenSortedAccordingToRunsAndWickets_shouldReturnPlayersInSorted()  {
        try{
            CricketAnalyser cricketAnalyser= new CricketAnalyser(CricketAnalyser.CricketData.BATSMEN_BOWLERS);
            cricketAnalyser.loadCricketData(BATSMEN_DATA_FILE_PATH,BOWLERS_DATA_FILE_PATH);
            String sortedData = cricketAnalyser.getSortedData(CricketAnalyser.CompareBy.RUNS_WITH_WICKETS);
            CricketDataDAO[] sorted =new Gson().fromJson(sortedData, CricketDataDAO[].class);
            Assert.assertEquals("Marcus Stoinis",sorted[0].player);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }
}


