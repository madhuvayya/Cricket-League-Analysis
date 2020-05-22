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

    public String getSortedCricketDataAccordingToAverages(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.average);
        return sort(iplCSVComparator,data);
    }

    public String getSortedCricketDataAccordingToStrikeRates(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.strikeRate);
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
        Comparator<CricketDataDAO> compareByStrikeRate = Comparator.comparing(iplData -> iplData.strikeRate);
        Comparator<CricketDataDAO> compareBySixes = compareByStrikeRate.thenComparing(iplData -> iplData.sixes);
        Comparator<CricketDataDAO> compareByFours = compareBySixes.thenComparing(iplData -> iplData.fours);
        return this.sort(compareByFours,data);
    }

    public String getSortedCricketDataAccordingToGreatAveragesBestStrikeRates(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByAverages = Comparator.comparing(iplData -> iplData.average);
        Comparator<CricketDataDAO> compareByStrikeRates = compareByAverages.thenComparing(iplData -> iplData.sixes);
        return this.sort(compareByStrikeRates,data);
    }

    public String getSortedCricketDataAccordingToRunsAndAverages(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByRuns = Comparator.comparing(iplData -> iplData.runs);
        Comparator<CricketDataDAO> compareByRunsWithAverage = compareByRuns.thenComparing(iplData -> iplData.average);
        return this.sort(compareByRunsWithAverage,data);
    }

    public String getSortedCricketDataAccordingToEconomy(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByEconomy = Comparator.comparing(iplData -> iplData.economy);
        return this.sort(compareByEconomy,data);
    }

    public String getSortedCricketDataAccordingToStrikeRateWithFiveAndFourWickets(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByStrikeRate = Comparator.comparing(iplData -> iplData.strikeRate);
        Comparator<CricketDataDAO> compareByFiveWickets = compareByStrikeRate.thenComparing(iplData -> iplData.sixes);
        Comparator<CricketDataDAO> compareByFourWickets = compareByFiveWickets.thenComparing(iplData -> iplData.fours);
        return this.sort(compareByFourWickets,data);
    }

    public String getSortedCricketDataAccordingToAveragesAndStrikeRates(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByAverages = Comparator.comparing(iplData -> iplData.average);
        Comparator<CricketDataDAO> compareByStrikeRate = compareByAverages.thenComparing(iplData -> iplData.strikeRate);
        return this.sort(compareByStrikeRate,data);
    }

    public String getSortedCricketDataAccordingToWicketsAndAverages(CricketData data) throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByWickets = Comparator.comparing(iplData -> iplData.wickets);
        Comparator<CricketDataDAO> compareByAverages = compareByWickets.thenComparing(iplData -> iplData.average);
        return this.sort(compareByAverages,data);
    }

    private String sort(Comparator<CricketDataDAO> cricketLeagueCSV,CricketData data) throws CricketAnalyserException {
        if(cricketDataMap==null || cricketDataMap.size()==0){
            throw new CricketAnalyserException("No League Data",
                    CricketAnalyserException.ExceptionType.DATA_NOT_APPROPRIATE);
        }
        List sortedData= cricketDataMap.values()
                                                .stream()
                                                .sorted(cricketLeagueCSV)
                                                .map(cricketDataDAO -> cricketDataDAO.getCensusDTO(data))
                                                .collect(Collectors.toList());
        return new Gson().toJson(sortedData);
    }

}
