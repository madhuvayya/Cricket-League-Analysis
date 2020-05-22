package cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyserTest {

        private static final String BATSMEN_DATA_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
        private static final String BATSMEN_DATA_WRONG_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
        private static final String BATSMEN_DATA_WRONG_TYPE_FILE_PATH = "./src/test/java/resources/IPL2019FactsheetMostRuns.txt";
        private static final String BATSMEN_DATA_PATH_WITH_INCORRECT_DELIMITER = "./src/test/java/resources/IPL2019FactsheetMostRunsIncorrectDelimiter.csv";
        private static final String BATSMEN_DATA_FILE_PATH_PATH_WITH_INCORRECT_HEADER = "./src/test/java/resources/IPL2019FactsheetMostRunsIncorrectHeader.csv";
        private static final String BOWLERS_DATA_FILE_PATH = "./src/test/java/resources/IPL2019FactsheetMostRuns.csv";

        @Test
        public void givenIPL2019MostRunsCSVFile_ReturnsCorrectRecords() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                int numOfPlayers = cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
                Assert.assertEquals(100, numOfPlayers);
            } catch (CricketAnalyserException cricketAnalyserException) { }
        }

        @Test
        public void givenIPL2019MostRunsCSVFile_ReturnsInorrectRecords() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                int numOfPlayers = cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
                Assert.assertNotEquals(120, numOfPlayers);
            } catch (CricketAnalyserException cricketAnalyserException) { }
        }

        @Test
        public void givenIPL2019MostRunsData_WithWrongFileType_ShouldThrowException() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_WRONG_TYPE_FILE_PATH);
            } catch (CricketAnalyserException e) {
                Assert.assertEquals(CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenIPL2019MostRunsData_WithWrongFilePath_ShouldThrowException() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_WRONG_FILE_PATH);
            } catch (CricketAnalyserException e) {
                Assert.assertEquals(CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenIPL2019MostRunsData_WithIncorrectDelimiter_ShouldThrowException() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_PATH_WITH_INCORRECT_DELIMITER);
            } catch (CricketAnalyserException e) {
                Assert.assertEquals(CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenIPL2019MostRunsData_WithIncorrectHeader_ShouldThrowException() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_FILE_PATH_PATH_WITH_INCORRECT_HEADER);
            } catch (CricketAnalyserException e) {
                Assert.assertEquals(CricketAnalyserException.ExceptionType.CSV_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenIPL2019MostRunsData_WhenSortDataAccordingToAverages_ShouldReturnTopAverageBatsMen() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_WRONG_FILE_PATH);
                String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAverages(CricketAnalyser.CricketData.BATSMEN);
                CricketDataDAO[] sorted = new Gson().fromJson(sortedMostRunsData, CricketDataDAO[].class);
                Assert.assertEquals("MS DHoni",sorted[sorted.length-1].player);
            } catch (CricketAnalyserException e){ }
        }

        @Test
        public void givenIPL2019MostRunsData_WhenSortDataAccordingToAverages_ShouldReturnSecondTopAverageBatsMen() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_WRONG_FILE_PATH);
                String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAverages(CricketAnalyser.CricketData.BATSMEN);
                BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
                Assert.assertEquals("Harpreet Brar",sorted[0].player);
            } catch (CricketAnalyserException e){ }
        }

        @Test
        public void givenIPL2019MostRunsData_WhenSortDataAccordingToStrikeRates_ShouldReturnTopAverageBatsMen() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN, BATSMEN_DATA_WRONG_FILE_PATH);
                String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRates(CricketAnalyser.CricketData.BATSMEN);
                BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
                Assert.assertNotEquals("Ishant Sharma",sorted[0].player);
            } catch (CricketAnalyserException e){ }
        }


        @Test
        public void givenIPL2019MostRunsData_WhenSortDataAccordingToStrikeRates_ShouldReturnSecondTopStrikeRateBatsMan() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
                String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRates(CricketAnalyser.CricketData.BATSMEN);
                BatsMen[] iplCSV = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
                Assert.assertNotEquals("Ishant Sharma",iplCSV[iplCSV.length-1].player);
            } catch (CricketAnalyserException e){ }
        }

        @Test
        public void givenIPL2019MostRunsData_WhenSortDataAccordingToFours_ShouldReturnBatsManWithMoreFours() {
            try {
                CricketAnalyser cricketAnalyser = new CricketAnalyser();
                cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
                String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToFours(CricketAnalyser.CricketData.BATSMEN);
                BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
                Assert.assertEquals("Shikhar Dhawan", sorted[sorted.length-1].player);
            } catch (CricketAnalyserException e) { }
        }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToFours_ShouldNotMatch() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToFours(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertNotEquals("Harpreet Brar", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToSixes_ShouldReturnBatsManWithMoreSixes() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToSixes(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("Andre Russel", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToSixes_ShouldNotMatch() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToSixes(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertNotEquals("Ishant Sharma", sorted[sorted.length-1].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToStrikeRateWithMaximumFoursAndSixes_ShouldReturnPlayer() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToBestStrikeRateWithSixesAndFours(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("Andre Russell", sorted[0].player);
            Assert.assertEquals("Shakib Al Hasan", sorted[99].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortDataAccordingToStrikeRateWithMaximumFoursAndSixes_ShouldNotMatch() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToBestStrikeRateWithSixesAndFours(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertNotEquals("MS DHoni", sorted[sorted.length-1].player);
            Assert.assertNotEquals("Ishant Sharma", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortAccordingToGreatAveragesBestStrikeRates_ShouldReturnPlayers() {
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
    public void givenIPL2019MostRunsData_WhenSortAccordingToRunsAndAverages_ShouldReturnPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BATSMEN,BATSMEN_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToRunsAndAverages(CricketAnalyser.CricketData.BATSMEN);
            BatsMen[] sorted = new Gson().fromJson(sortedMostRunsData, BatsMen[].class);
            Assert.assertEquals("David Warner ", sorted[sorted.length-1].player);
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
            Assert.assertEquals("Krishnappa Gowtham", sorted[sorted.length-1].player);
            Assert.assertEquals("Yusuf Pathan", sorted[2].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToStrikeRate_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAverages(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedMostRunsData, Bowlers[].class);
            Assert.assertEquals("Alzarri Joseph", sorted[sorted.length-1].player);
            Assert.assertEquals("Liam Livingstone", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToEconomy_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToEconomy(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedMostRunsData, Bowlers[].class);
            Assert.assertEquals("Shivam Dube", sorted[sorted.length-1].player);
            Assert.assertEquals("Ben Cutting", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToStrikeRateWithFiveAndFourWickets_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToStrikeRateWithFiveAndFourWickets(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedMostRunsData, Bowlers[].class);
            Assert.assertEquals("Lasith Malinga", sorted[sorted.length-1].player);
            Assert.assertEquals("Yusuf Pathan", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToAveragesAndStrikeRates_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToAveragesAndStrikeRates(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedMostRunsData, Bowlers[].class);
            Assert.assertEquals("Krishnappa Gowtham", sorted[sorted.length-1].player);
            Assert.assertEquals("Yusuf Pathan", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }

    @Test
    public void givenIPL2019MostWicketsData_WhenSortAccordingToWicketsAndAverages_ShouldReturnTopPlayers() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.loadCricketData(CricketAnalyser.CricketData.BOWLERS, BOWLERS_DATA_FILE_PATH);
            String sortedMostRunsData = cricketAnalyser.getSortedCricketDataAccordingToWicketsAndAverages(CricketAnalyser.CricketData.BOWLERS);
            Bowlers[] sorted = new Gson().fromJson(sortedMostRunsData, Bowlers[].class);
            Assert.assertEquals("Imran Tahir", sorted[sorted.length-1].player);
            Assert.assertEquals("Yusuf Pathan", sorted[0].player);
        } catch (CricketAnalyserException e) { }
    }
}
