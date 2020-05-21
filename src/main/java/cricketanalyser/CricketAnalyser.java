package cricketanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {

    Map<String, CricketDataDAO> cricketDataMap = null;

    public  int loadCricketData(String csvFilePath) throws CricketAnalyserException {
        CricketDataLoader cricketDataLoader = new CricketDataLoader();
        Map<String, CricketDataDAO> cricketDataMap = cricketDataLoader.loadCricketData(csvFilePath);
        return cricketDataMap.size();
    }

    public String getSortedCricketDataAccordingToBattingAverages() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.average);
        return sort(iplCSVComparator);
    }

    public String getSortedCricketDataAccordingToStrikeRates() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.strikeRate);
        return sort(iplCSVComparator);
    }

    public String getSortedCricketDataAccordingToFours() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.fours);
        return sort(iplCSVComparator);
    }

    public String getSortedCricketDataAccordingToSixes() throws CricketAnalyserException {
        Comparator<CricketDataDAO> iplCSVComparator =Comparator.comparing(sortBy->sortBy.sixes);
        return sort(iplCSVComparator);
    }

    public String getSortedCricketDataAccordingToBestStrikeRateWithSixesAndFours() throws CricketAnalyserException {
        Comparator<CricketDataDAO> compareByStrikeRate = Comparator.comparing(iplData -> iplData.strikeRate);
        Comparator<CricketDataDAO> compareBySixes = compareByStrikeRate.thenComparing(iplData -> iplData.sixes);
        Comparator<CricketDataDAO> compareByFours = compareBySixes.thenComparing(iplData -> iplData.fours);
        return this.sort(compareByFours);
    }

    private String sort(Comparator cricketLeagueCSV) throws CricketAnalyserException {
        if(cricketDataMap==null || cricketDataMap.size()==0){
            throw new CricketAnalyserException("No League Data",
                    CricketAnalyserException.ExceptionType.DATA_NOT_APPROPRIATE);
            }
            List sortedData= (List) cricketDataMap.values().stream()
                                         .sorted(cricketLeagueCSV)
                                         .collect(Collectors.toList());
            String sortedDataInJson=new Gson().toJson(sortedData);
            return sortedDataInJson;
    }
}
