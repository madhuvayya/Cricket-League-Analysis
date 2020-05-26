package cricketanalyser;

public class CricketDataDAO {

    public String player;
    public int fiveWickets;
    public int fourWickets;
    public int runs;
    public int highScore;
    public double battingAverage;
    public double bowlingAverage;
    public double battingStrikeRate;
    public double bowlingStrikeRate;
    public int fours;
    public int sixes;
    public double economy;
    public int wickets;

    public CricketDataDAO(BatsMenData mostRunsCSV) {
        this.player = mostRunsCSV.player;
        this.runs = mostRunsCSV.runs;
        this.highScore = mostRunsCSV.highScore;
        this.battingAverage = mostRunsCSV.battingAverage;
        this.battingStrikeRate = mostRunsCSV.battingStrikeRate;
        this.fours = mostRunsCSV.fours;
        this.sixes = mostRunsCSV.sixes;
    }

    public CricketDataDAO(BowlersData mostWicketsCSV) {
        this.player = mostWicketsCSV.player;
        this.economy = mostWicketsCSV.economy;
        this.bowlingAverage = mostWicketsCSV.bowlingAverage;
        this.bowlingStrikeRate = mostWicketsCSV.bowlingStrikeRate;
        this.fourWickets = mostWicketsCSV.fourWickets;
        this.fiveWickets = mostWicketsCSV.fiveWickets;
    }

    public Object getCricketDataDTO(CricketAnalyser.CricketData data) {
        if(data.equals(CricketAnalyser.CricketData.BATSMEN))
            return new BatsMenData(player,runs, battingAverage, battingStrikeRate,fours,sixes);
        if (data.equals(CricketAnalyser.CricketData.BATSMEN))
            return new BowlersData(player,wickets, battingAverage,economy, battingStrikeRate,fourWickets,fiveWickets);
        return new BatsMenBowlerData(player,fours,sixes,runs,battingAverage, bowlingAverage,
                                                battingStrikeRate,wickets);
    }
}
