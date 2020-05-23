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

    public CricketDataDAO(BatsMen mostRunsCSV) {
        this.player = mostRunsCSV.player;
        this.runs = mostRunsCSV.runs;
        this.highScore = mostRunsCSV.highScore;
        this.battingAverage = mostRunsCSV.battingAverage;
        this.battingStrikeRate = mostRunsCSV.battingStrikeRate;
        this.fours = mostRunsCSV.fours;
        this.sixes = mostRunsCSV.sixes;
    }

    public CricketDataDAO(Bowlers mostWicketsCSV) {
        this.player = mostWicketsCSV.player;
        this.runs = mostWicketsCSV.runs;
        this.economy = mostWicketsCSV.economy;
        this.bowlingAverage = mostWicketsCSV.bowlingAverage;
        this.bowlingStrikeRate = mostWicketsCSV.bowlingStrikeRate;
        this.fourWickets = mostWicketsCSV.fourWickets;
        this.fiveWickets = mostWicketsCSV.fiveWickets;
    }

    public Object getCensusDTO(CricketAnalyser.CricketData data) {
        if(data.equals(CricketAnalyser.CricketData.BATSMEN))
            return new BatsMen(player,runs, battingAverage, battingStrikeRate,fours,sixes);
        else if (data.equals(CricketAnalyser.CricketData.BATSMEN))
            return new Bowlers(player,wickets, battingAverage,economy, battingStrikeRate,fourWickets,fiveWickets);
        else
            return new BatsMenBowlerCombination(player,fours,sixes,runs,battingAverage, bowlingAverage,battingStrikeRate
                                                ,wickets);
    }
}
