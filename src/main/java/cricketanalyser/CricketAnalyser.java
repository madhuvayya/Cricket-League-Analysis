package cricketanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {

    public enum CricketData {
        BATSMEN,
        BOWLERS,
        BATSMEN_BOWLERS
    }

    private final CricketData cricketData;

    enum CompareBy {
        BATSMAN_AVERAGE,BATSMAN_STRIKE, FOURS, SIXES, RUNS, SIXES_AND_FOURS,
        STRIKE_RATE_WITH_SIXES_AND_FOURS, BATSMAN_AVERAGE_WITH_STRIKE, BATSMAN_RUNS_WITH_AVERAGE,
        FOUR_AND_FIVE_WICKETS, BOWLER_AVERAGE, BOWLER_STRIKE_RATE, BOWLER_WICKETS,
        BOWLER_STRIKE_WITH_FOUR_AND_FIVE_WICKETS, BOWLER_ECONOMY,BOWLER_AVERAGE_WITH_STRIKE,
        BOWLER_WICKETS_WITH_AVERAGE,RUNS_WITH_WICKETS, BATTING_AVERAGE_WITH_BOWLING_AVERAGE
    }

    public CricketAnalyser(CricketData cricketData) {
        this.cricketData = cricketData;
    }

    Map<String,CricketDataDAO> cricketDataMap = null;

    public int loadCricketData(String... csvFilePath) throws CricketAnalyserException {
        cricketDataMap = new CricketDataLoaderFactory().getCricketData(cricketData,csvFilePath);
        return cricketDataMap.size();
    }

    public String getSortedData(CompareBy compareBy) throws CricketAnalyserException {
        if(cricketDataMap == null || cricketDataMap.size() == 0){
            throw new CricketAnalyserException("No League Data",
                                                CricketAnalyserException.ExceptionType.NO_DATA);
        }
        Comparator<CricketDataDAO> comparator = new Comparators().getComparator(compareBy);
        List sortedData= cricketDataMap.values().stream()
                                                .sorted(comparator)
                                                .map(cricketDataDAO -> cricketDataDAO.getCricketDataDTO(cricketData))
                                                .collect(Collectors.toList());
        return new Gson().toJson(sortedData);
    }
}
