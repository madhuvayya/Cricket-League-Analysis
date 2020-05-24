package cricketanalyser;

import java.util.Map;

public class CricketDataLoaderFactory {

    public Map<String, CricketDataDAO> getCricketData(CricketAnalyser.CricketData cricket, String ...csvFilePath)
                                                        throws CricketAnalyserException {
        if (cricket.equals(CricketAnalyser.CricketData.BATSMEN))
            return new BatsMenDataAdapter().loadData(csvFilePath[0]);
        else if (cricket.equals(CricketAnalyser.CricketData.BOWLERS))
            return new BowlerDataAdapter().loadData(csvFilePath[0]);
        return new BatsMenBowlerCombinedDataAdapter().loadData(csvFilePath);
    }
}
