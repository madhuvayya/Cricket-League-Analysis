package cricketanalyser;

import java.util.Map;

public class BatsMenDataAdapter extends CricketDataAdapter {

    public Map<String, CricketDataDAO> loadData(String ...csvFilePath) throws CricketAnalyserException {
        return super.getCricketData(BatsMen.class,csvFilePath[0]);
    }

}
