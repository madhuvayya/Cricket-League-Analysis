package cricketanalyser;

public class BatsMenBowlerCombinationData {

    public String player;
    public int four;
    public int six;
    public int runs;
    public double battingAverage;
    public double bowlerAverage;
    public double battingStrikeRate;
    public int wickets;

    public BatsMenBowlerCombinationData(String player, int four, int six, int runs, double battingAverage,
                                        double bowlerAverage, double battingStrikeRate, int wickets) {
        this.player = player;
        this.four = four;
        this.six = six;
        this.runs = runs;
        this.battingAverage = battingAverage;
        this.bowlerAverage = bowlerAverage;
        this.battingStrikeRate = battingStrikeRate;
        this.wickets = wickets;
    }
}
