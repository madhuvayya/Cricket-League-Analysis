package cricketanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {

    enum CricketData {
        BATSMEN,BOWLERS,BATSMEN_BOWLERS
    }

    public CricketAnalyser() {
    }

    Map<String,CricketDataDAO> cricketDataMap = null;

    public  int loadCricketData(CricketData data,String filePath) throws CricketAnalyserException {
        cricketDataMap = new CricketDataLoaderFactory().getCricketData(data,filePath);
        return cricketDataMap.size();
    }

    public String getSortedCricketDataAccordingToAverages(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.battingAverage);
        return sort(iplCSVComparator,data);
    }

    public String getSortedCricketDataAccordingToStrikeRates(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.battingStrikeRate);
        return sort(iplCSVComparator,data);
    }

    public String getSortedCricketDataAccordingToFours(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.fours);
        return sort(iplCSVComparator,data);
    }

    public String getSortedCricketDataAccordingToSixes(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.sixes);
        return sort(iplCSVComparator,data);
    }

    public String getSortedCricketDataAccordingToBestStrikeRateWithSixesAndFours(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareBySixes = Comparator.comparing(iplData -> iplData.sixes);
        Comparator<CricketDataDAO> compareByFours = compareBySixes.thenComparing(iplData -> iplData.fours);
        Comparator<CricketDataDAO> compareByStrikeRate =compareByFours.thenComparing(iplData -> iplData.battingStrikeRate);
        return this.sort(compareByStrikeRate,data);
    }

    public String getSortedCricketDataAccordingToGreatAveragesBestStrikeRates(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByAverages = Comparator.comparing(iplData -> iplData.battingAverage);
        Comparator<CricketDataDAO> compareByStrikeRates = compareByAverages.thenComparing(iplData -> iplData.battingStrikeRate);
        return this.sort(compareByStrikeRates,data);
    }

    public String getSortedCricketDataAccordingToRunsAndAverages(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByRuns = Comparator.comparing(iplData -> iplData.runs);
        Comparator<CricketDataDAO> compareByRunsWithAverage = compareByRuns.thenComparing(iplData -> iplData.battingAverage);
        return this.sort(compareByRunsWithAverage,data);
    }

    public String getSortedCricketDataAccordingToEconomy(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByEconomy = Comparator.comparing(iplData -> iplData.economy);
        return this.sort(compareByEconomy,data);
    }

    public String getSortedCricketDataAccordingToStrikeRateWithFiveAndFourWickets(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByFiveWickets = Comparator.comparing(iplData -> iplData.fiveWickets);
        Comparator<CricketDataDAO> compareByFourWickets = compareByFiveWickets.thenComparing(iplData -> iplData.fourWickets);
        Comparator<CricketDataDAO> compareByStrikeRate =  compareByFourWickets.thenComparing(iplData -> iplData.battingStrikeRate);
        return this.sort(compareByStrikeRate,data);
    }

    public String getSortedCricketDataAccordingToAveragesAndStrikeRates(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByAverages = Comparator.comparing(iplData -> iplData.battingAverage);
        Comparator<CricketDataDAO> compareByStrikeRate = compareByAverages.thenComparing(iplData -> iplData.battingStrikeRate);
        return this.sort(compareByStrikeRate,data);
    }

    public String getSortedCricketDataAccordingToWicketsAndAverages(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByWickets = Comparator.comparing(iplData -> iplData.wickets);
        Comparator<CricketDataDAO> compareByAverages = compareByWickets.thenComparing(iplData -> iplData.battingAverage);
        return this.sort(compareByAverages,data);
    }

    public String getSortedDataAccordingToBattingAndBowlingAverage(CricketData cricket) throws CricketAnalyserException {
        Comparator<CricketDataDAO> sortByBattingAverage = Comparator.comparing(iplData -> iplData.battingAverage);
        Comparator<CricketDataDAO> sortByBattingAndBowlingAverage = sortByBattingAverage.
                                                                    thenComparing(iplData -> iplData.bowlingAverage);
        return sort(sortByBattingAndBowlingAverage.reversed(),cricket);
    }

    private String sort(Comparator<CricketDataDAO> cricketCSV,CricketData data) throws CricketAnalyserException {
        if(cricketDataMap==null || cricketDataMap.size()==0){
            throw new CricketAnalyserException("No League Data",
                    CricketAnalyserException.ExceptionType.NO_DATA);
        }
        List sortedData= cricketDataMap.values().stream()
                                                .sorted(cricketCSV)
                                                .map(cricketDataDAO -> cricketDataDAO.getCensusDTO(data))
                                                .collect(Collectors.toList());
        return new Gson().toJson(sortedData);
    }

}
