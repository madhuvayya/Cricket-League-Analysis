package cricketanalyser;

import java.util.Map;

public class BowlerDataAdapter extends CricketDataAdapter{

    public Map<String, CricketDataDAO> loadData(String ...csvFilePath) throws CricketAnalyserException {
        return super.getCricketData(BowlersData.class,csvFilePath[0]);
    }
}
