package cricketanalyser;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CricketAnalyser {

    Map<String, CricketDataDAO> cricketDataMap = null;

    public int loadCricketData(String csvFilePath) throws CricketAnalyserException {
        CricketDataLoader cricketDataLoader = new CricketDataLoader();
        cricketDataMap = cricketDataLoader.loadCricketData(csvFilePath);
        return cricketDataMap.size();
    }

    public String getSortedCricketDataAccordingToBattingAverages() throws CricketAnalyserException {
        Comparator<CricketDataDAO> cricketDataDAOComparator = Comparator.comparing(cricketData -> cricketData.average);
        return this.getSortedIPLData(cricketDataDAOComparator);
    }

    public String getSortedCricketDataAccordingToStrikeRates() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplDataDAOComparator = Comparator.comparing(iplData -> iplData.strikeRate);
        return this.getSortedIPLData(iplDataDAOComparator);
    }

    public String getSortedCricketDataAccordingToFours() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplDataDAOComparator = Comparator.comparing(iplData -> iplData.fours);
        return this.getSortedIPLData(iplDataDAOComparator);
    }

    private String getSortedIPLData(Comparator<CricketDataDAO> cricketDataDAOComparator) throws CricketAnalyserException {
        if(cricketDataMap == null || cricketDataMap.size() ==0 ) {
            throw new CricketAnalyserException("No Census Data", CricketAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List sortedCricketData = cricketDataMap.values().stream().
                sorted(cricketDataDAOComparator).
                map(cricketDataDAO -> cricketDataDAO.getCricketDataDTO()).
                collect(Collectors.toList());
        return new Gson().toJson(sortedCricketData);
    }
}
