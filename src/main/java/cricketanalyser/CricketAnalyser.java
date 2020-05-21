package cricketanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {

    enum CricketData {
        BATSMEN,BOWLERS
    }

    Map<String, CricketDataDAO> cricketDataMap = null;

    public  int loadCricketData(CricketData data,String csvFilePath) throws CricketAnalyserException {
        CricketDataLoader cricketDataLoader = new CricketDataLoader();
        Map<String, CricketDataDAO> cricketDataMap = cricketDataLoader.loadBatsMenData(data,csvFilePath);
        return cricketDataMap.size();
    }

    public String getSortedCricketDataAccordingToAverages() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.average);
        return sort(iplCSVComparator);
    }

    public String getSortedCricketDataAccordingToStrikeRates() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.strikeRate);
        return sort(iplCSVComparator);
    }

    public String getSortedCricketDataAccordingToFours() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.fours);
        return sort(iplCSVComparator);
    }

    public String getSortedCricketDataAccordingToSixes() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.sixes);
        return sort(iplCSVComparator);
    }

    public String getSortedCricketDataAccordingToBestStrikeRateWithSixesAndFours() throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByStrikeRate = Comparator.comparing(iplData -> iplData.strikeRate);
        Comparator<CricketDataDAO> compareBySixes = compareByStrikeRate.thenComparing(iplData -> iplData.sixes);
        Comparator<CricketDataDAO> compareByFours = compareBySixes.thenComparing(iplData -> iplData.fours);
        return this.sort(compareByFours);
    }

    public String getSortedCricketDataAccordingToGreatAveragesBestStrikeRates() throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByAverages = Comparator.comparing(iplData -> iplData.average);
        Comparator<CricketDataDAO> compareByStrikeRates = compareByAverages.thenComparing(iplData -> iplData.sixes);
        return this.sort(compareByStrikeRates);
    }

    public String getSortedCricketDataAccordingToRunsAndAverages() throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByRuns = Comparator.comparing(iplData -> iplData.runs);
        Comparator<CricketDataDAO> compareByRunsWithAverage = compareByRuns.thenComparing(iplData -> iplData.average);
        return this.sort(compareByRunsWithAverage);
    }

    public String getSortedCricketDataAccordingToEconomy() throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByEconomy = Comparator.comparing(iplData -> iplData.economy);
        return this.sort(compareByEconomy);
    }

    public String getSortedCricketDataAccordingToStrikeRateWithFiveAndFourWickets() throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByStrikeRate = Comparator.comparing(iplData -> iplData.strikeRate);
        Comparator<CricketDataDAO> compareByFiveWickets = compareByStrikeRate.thenComparing(iplData -> iplData.sixes);
        Comparator<CricketDataDAO> compareByFourWickets = compareByFiveWickets.thenComparing(iplData -> iplData.fours);
        return this.sort(compareByFourWickets);
    }

    public String getSortedCricketDataAccordingToAveragesAndStrikeRates() throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByAverages = Comparator.comparing(iplData -> iplData.average);
        Comparator<CricketDataDAO> compareByStrikeRate = compareByAverages.thenComparing(iplData -> iplData.strikeRate);
        return this.sort(compareByStrikeRate);
    }

    public String getSortedCricketDataAccordingToWicketsAndAverages() throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByWickets = Comparator.comparing(iplData -> iplData.wickets);
        Comparator<CricketDataDAO> compareByAverages = compareByWickets.thenComparing(iplData -> iplData.average);
        return this.sort(compareByAverages);
    }

    private String sort(Comparator<CricketDataDAO> cricketLeagueCSV) throws CricketAnalyserException {
        if(cricketDataMap==null || cricketDataMap.size()==0){
            throw new CricketAnalyserException("No League Data",
                    CricketAnalyserException.ExceptionType.DATA_NOT_APPROPRIATE);
        }
        List sortedData= (List) cricketDataMap.values().stream()
                                         .sorted(cricketLeagueCSV)
                                         .collect(Collectors.toList());
        return new Gson().toJson(sortedData);
    }

}
