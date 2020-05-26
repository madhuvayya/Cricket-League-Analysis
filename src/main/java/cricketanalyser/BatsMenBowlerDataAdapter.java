package cricketanalyser;

import java.util.HashMap;
import java.util.Map;

public class BatsMenBowlerDataAdapter extends CricketDataAdapter{

    Map<String, CricketDataDAO> batsMenData = new HashMap<>();
    Map<String, CricketDataDAO> bowlersData = new HashMap<>();

    public Map<String, CricketDataDAO> loadData(String... csvFilePath) throws CricketAnalyserException {
        batsMenData = super.getCricketData(BatsMenData.class,csvFilePath[0]);
        bowlersData = super.getCricketData(BowlersData.class,csvFilePath[1]);
        bowlersData.values().stream()
                .filter(data -> batsMenData.get(data.player) != null)
                .forEach(data -> {
                    batsMenData.get(data.player).bowlingAverage = data.bowlingAverage;
                    batsMenData.get(data.player).wickets = data.wickets;
                });
        return batsMenData;
    }
}
