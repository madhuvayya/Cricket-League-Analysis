package cricketanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Comparators {

    static Map<CricketAnalyser.CompareBy , Comparator<CricketDataDAO>> comparatorsMap = new HashMap<>();

    static  {
        Comparator<CricketDataDAO> compareByBattingStrike = Comparator.comparing(data -> data.battingStrikeRate);
        comparatorsMap.put(CricketAnalyser.CompareBy.BATSMAN_STRIKE, compareByBattingStrike);
        Comparator<CricketDataDAO> compareByBattingAverage = Comparator.comparing(data -> data.battingAverage);
        comparatorsMap.put(CricketAnalyser.CompareBy.BATSMAN_AVERAGE,compareByBattingAverage);
        Comparator<CricketDataDAO> compareByFours = Comparator.comparing(data -> data.fours);
        comparatorsMap.put(CricketAnalyser.CompareBy.FOURS,compareByFours);
        Comparator<CricketDataDAO> compareBySixes = Comparator.comparing(data -> data.sixes);
        comparatorsMap.put(CricketAnalyser.CompareBy.SIXES,compareBySixes);
        Comparator<CricketDataDAO> compareByRuns = Comparator.comparing(data -> data.runs);
        comparatorsMap.put(CricketAnalyser.CompareBy.RUNS,compareByRuns);
        Comparator<CricketDataDAO> compareBySixAndFour = Comparator.comparing(data -> (data.sixes * 6 + data.fours * 4));
        comparatorsMap.put(CricketAnalyser.CompareBy.SIXES_AND_FOURS,compareBySixAndFour);
        Comparator<CricketDataDAO> compareByBattingAverageWithStrike = compareByBattingAverage.thenComparing(compareByBattingStrike);
        comparatorsMap.put(CricketAnalyser.CompareBy.BATSMAN_AVERAGE_WITH_STRIKE,compareByBattingAverageWithStrike);
        Comparator<CricketDataDAO> compareByStrikeWithSixesAndFours = compareBySixAndFour.thenComparing(compareByBattingStrike);
        comparatorsMap.put(CricketAnalyser.CompareBy.STRIKE_RATE_WITH_SIXES_AND_FOURS,compareByStrikeWithSixesAndFours);
        Comparator<CricketDataDAO> compareByRunsWithAverage = compareByRuns.thenComparing(compareByBattingAverage);
        comparatorsMap.put(CricketAnalyser.CompareBy.BATSMAN_RUNS_WITH_AVERAGE,compareByRunsWithAverage);
        Comparator<CricketDataDAO> compareByBowlerAverage = Comparator.comparing(data -> data.bowlingAverage);
        comparatorsMap.put(CricketAnalyser.CompareBy.BOWLER_AVERAGE,compareByBowlerAverage);
        Comparator<CricketDataDAO> compareByBowlerStrikeRate = Comparator.comparing(data -> data.bowlingStrikeRate);
        comparatorsMap.put(CricketAnalyser.CompareBy.BOWLER_STRIKE_RATE,compareByBowlerStrikeRate);
        Comparator<CricketDataDAO> compareByEconomy = Comparator.comparing(data -> data.economy);
        comparatorsMap.put(CricketAnalyser.CompareBy.BOWLER_ECONOMY,compareByEconomy);
        Comparator<CricketDataDAO> compareByFiveAndFourWickets = Comparator.comparing(data -> (data.fiveWickets * 5 + data.fourWickets * 4));
        comparatorsMap.put(CricketAnalyser.CompareBy.FOUR_AND_FIVE_WICKETS,compareByFiveAndFourWickets);
        Comparator<CricketDataDAO> compareByStrikeWithFiveAndFourWickets = compareByFiveAndFourWickets.thenComparing(compareByBowlerStrikeRate);
        comparatorsMap.put(CricketAnalyser.CompareBy.BOWLER_STRIKE_WITH_FOUR_AND_FIVE_WICKETS,compareByStrikeWithFiveAndFourWickets);
        Comparator<CricketDataDAO> compareByAverageWithStrike = compareByBowlerAverage.thenComparing(compareByBowlerStrikeRate);
        comparatorsMap.put(CricketAnalyser.CompareBy.BOWLER_AVERAGE_WITH_STRIKE,compareByAverageWithStrike);
        Comparator<CricketDataDAO> compareByWickets = Comparator.comparing(iplData -> iplData.wickets);
        comparatorsMap.put(CricketAnalyser.CompareBy.BOWLER_WICKETS,compareByWickets);
        Comparator<CricketDataDAO> compareByWicketsWithAverage = compareByWickets.thenComparing(compareByBowlerAverage);
        comparatorsMap.put(CricketAnalyser.CompareBy.BOWLER_WICKETS_WITH_AVERAGE,compareByWicketsWithAverage);
        Comparator<CricketDataDAO> compareByBattingAndBowlingAverage = compareByBattingAverage.thenComparing(compareByBowlerAverage);
        comparatorsMap.put(CricketAnalyser.CompareBy.BATTING_AVERAGE_WITH_BOWLING_AVERAGE,compareByBattingAndBowlingAverage);
        Comparator<CricketDataDAO> compareByRunsAndWickets = compareByRuns.thenComparing(compareByWickets);
        comparatorsMap.put(CricketAnalyser.CompareBy.RUNS_WITH_WICKETS,compareByRunsAndWickets);
    }

    public Comparator<CricketDataDAO> getComparator(CricketAnalyser.CompareBy compareBy) {
        return comparatorsMap.get(compareBy);
    }
}
