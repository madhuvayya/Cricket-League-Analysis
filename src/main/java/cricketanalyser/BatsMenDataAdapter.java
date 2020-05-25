package cricketanalyser;

import java.util.Map;

public class BatsMenDataAdapter extends CricketDataAdapter {

    public Map<String, CricketDataDAO> loadData(String ...csvFilePath) throws CricketAnalyserException {
        return super.getCricketData(BatsMenData.class,csvFilePath[0]);
    }
}
