package cricketanalyser;

import java.util.HashMap;
import java.util.Map;

public class BatsMenBowlerCombinedDataAdapter extends CricketDataAdapter{

    Map<String, CricketDataDAO> batsmanData = new HashMap<>();
    Map<String, CricketDataDAO> bowlersData = new HashMap<>();

    public Map<String, CricketDataDAO> loadData(String... csvFilePath) throws CricketAnalyserException {
        batsmanData = super.getCricketData(BatsMenData.class,csvFilePath[0]);
        bowlersData = super.getCricketData(BowlersData.class,csvFilePath[1]);
        bowlersData.values().stream()
                .filter(iplData -> batsmanData.get(iplData.player) != null)
                .forEach(iplData -> {
                    batsmanData.get(iplData.player).bowlingAverage = iplData.bowlingAverage;
                    batsmanData.get(iplData.player).wickets = iplData.wickets;
                });
        return batsmanData;
    }
}
