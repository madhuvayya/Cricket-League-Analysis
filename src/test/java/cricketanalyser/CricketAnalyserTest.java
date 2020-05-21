package cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyserTest {

        private static final String IPL2019_MOST_RUNS_CSV_FILE_PATH = "./src/test/java/resources/IPL2019FactsheetMostRuns.csv";
        private static final String IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
        private static final String IPL2019_MOST_RUNS_WRONG_TYPE_FILE_PATH = "./src/test/java/resources/IPL2019FactsheetMostRuns.txt";
        private static final String IPL2019_MOST_RUNS_CSV_FILE_PATH_WITH_INCORRECT_DELIMITER = "./src/test/java/resources/IPL2019FactsheetMostRunsIncorrectDelimiter.csv";
        private static final String IPL2019_MOST_RUNS_CSV_FILE_PATH_WITH_INCORRECT_HEADER = "./src/test/java/resources/IPL2019FactsheetMostRunsIncorrectHeader.csv";
        private static final String IPL2019_MOST_WICKETS_CSV_FILE_PATH = "./src/test/java/resources/IPL2019FactsheetMostRuns.csv";

        @Test
        public void givenIPL2019MostRunsCSVFile_ReturnsCorrectRecords() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                int numOfPlayers = cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
                Assert.assertEquals(101, numOfPlayers);
            } catch (CricketAnalyserException cricketAnalyserException) { }
        }

        @Test
        public void givenIPL2019MostRunsCSVFile_ReturnsInorrectRecords() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                int numOfPlayers = cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
                Assert.assertNotEquals(120, numOfPlayers);
            } catch (CricketAnalyserException cricketAnalyserException) { }
        }

        @Test
        public void givenIPL2019MostRunsData_WithWrongFileType_ShouldThrowException() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_WRONG_TYPE_FILE_PATH);
            } catch (CricketAnalyserException e) {
                Assert.assertEquals(CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenIPL2019MostRunsData_WithWrongFilePath_ShouldThrowException() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH);
            } catch (CricketAnalyserException e) {
                Assert.assertEquals(CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenIPL2019MostRunsData_WithIncorrectDelimiter_ShouldThrowException() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH_WITH_INCORRECT_DELIMITER);
            } catch (CricketAnalyserException e) {
                Assert.assertEquals(CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenIPL2019MostRunsData_WithIncorrectHeader_ShouldThrowException() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH_WITH_INCORRECT_HEADER);
            } catch (CricketAnalyserException e) {
                Assert.assertEquals(CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenIPL2019MostRunsData_WhenSortDataAccordingToAverages_ShouldReturnTopAverageBatsMen() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH);
                String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAverages();
                CricketDataDAO[] sorted = new Gson().fromJson(sortedMostRunsData, CricketDataDAO[].class);
                Assert.assertEquals("MS DHoni",sorted[sorted.length-1].player);
            } catch (CricketAnalyserException e){ }
        }

        @Test
        public void givenIPL2019MostRunsData_WhenSortDataAccordingToAverages_ShouldReturnSecondTopAverageBatsMen() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH);
                String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAverages();
                IPLMostRunsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
                Assert.assertEquals("Harpreet Brar",sorted[0].player);
            } catch (CricketAnalyserException e){ }
        }

        @Test
        public void givenIPL2019MostRunsData_WhenSortDataAccordingToStrikeRates_ShouldReturnTopAverageBatsMen() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_WRONG_CSV_FILE_PATH);
                String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRates();
                IPLMostRunsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
                Assert.assertNotEquals("Ishant Sharma",sorted[0].player);
            } catch (CricketAnalyserException e){ }
        }


        @Test
        public void givenIPL2019MostRunsData_WhenSortDataAccordingToStrikeRates_ShouldReturnSecondTopStrikeRateBatsMan() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
                String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRates();
                IPLMostRunsCSV[] iplCSV = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
                Assert.assertNotEquals("Ishant Sharma",iplCSV[iplCSV.length-1].player);
            } catch (CricketAnalyserException e){ }
        }

        @Test
        public void givenIPL2019MostRunsData_WhenSortDataAccordingToFours_ShouldReturnBatsManWithMoreFours() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
                String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToFours();
                IPLMostRunsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
                Assert.assertEquals("Shikhar Dhawan", sorted[sorted.length-1].player);
            } catch (CricketAnalyserException e) { }
        }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToFours_ShouldNotMatch() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToFours();
            IPLMostRunsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
            Assert.assertNotEquals("Harpreet Brar", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToSixes_ShouldReturnBatsManWithMoreSixes() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToSixes();
            IPLMostRunsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
            Assert.assertEquals("Andre Russel", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToSixes_ShouldNotMatch() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToSixes();
            IPLMostRunsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
            Assert.assertNotEquals("Ishant Sharma", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToStrikeRateWithMaximumFoursAndSixes_ShouldReturnPlayer() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToBestStrikeRateWithSixesAndFours();
            IPLMostRunsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
            Assert.assertEquals("Andre Russell", sorted[0].player);
            Assert.assertEquals("Shakib Al Hasan", sorted[99].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToStrikeRateWithMaximumFoursAndSixes_ShouldNotMatch() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToBestStrikeRateWithSixesAndFours();
            IPLMostRunsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
            Assert.assertNotEquals("MS DHoni", sorted[sorted.length-1].player);
            Assert.assertNotEquals("Ishant Sharma", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortAccordingToGreatAveragesBestStrikeRates_ShouldReturnPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToGreatAveragesBestStrikeRates();
            IPLMostRunsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", sorted[sorted.length-1].player);
            Assert.assertEquals("Tim Southee", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortAccordingToRunsAndAverages_ShouldReturnPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,IPL2019_MOST_RUNS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToRunsAndAverages();
            IPLMostRunsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostRunsCSV[].class);
            Assert.assertEquals("David Warner ", sorted[sorted.length-1].player);
            Assert.assertEquals("Tim Southee", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToAverages_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS,IPL2019_MOST_WICKETS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAverages();
            IPLMostWicketsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostWicketsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", sorted[sorted.length-1].player);
            Assert.assertEquals("Yusuf Pathan", sorted[2].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToStrikeRate_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS,IPL2019_MOST_WICKETS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAverages();
            IPLMostWicketsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostWicketsCSV[].class);
            Assert.assertEquals("Alzarri Joseph", sorted[sorted.length-1].player);
            Assert.assertEquals("Liam Livingstone", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToEconomy_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS,IPL2019_MOST_WICKETS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToEconomy();
            IPLMostWicketsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostWicketsCSV[].class);
            Assert.assertEquals("Shivam Dube", sorted[sorted.length-1].player);
            Assert.assertEquals("Ben Cutting", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToStrikeRateWithFiveAndFourWickets_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS,IPL2019_MOST_WICKETS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRateWithFiveAndFourWickets();
            IPLMostWicketsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostWicketsCSV[].class);
            Assert.assertEquals("Lasith Malinga", sorted[sorted.length-1].player);
            Assert.assertEquals("Yusuf Pathan", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToAveragesAndStrikeRates_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS,IPL2019_MOST_WICKETS_CSV_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAveragesAndStrikeRates();
            IPLMostWicketsCSV[] sorted = new Gson().fromJson(sortedMostRunsData, IPLMostWicketsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", sorted[sorted.length-1].player);
            Assert.assertEquals("Yusuf Pathan", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }
}
