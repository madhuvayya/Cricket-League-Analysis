package cricketanalyser;

public class BatsMenBowlerCombination {

    public String player;
    public int four;
    public int six;
    public int runs;
    public double battingAverage;
    public double bowlerAverage;
    public double battingStrikeRate;

    public BatsMenBowlerCombination(String player, int four, int six, int runs, double battingAverage,
                                    double bowlerAverage, double battingStrikeRate) {
        this.player = player;
        this.four = four;
        this.six = six;
        this.runs = runs;
        this.battingAverage = battingAverage;
        this.bowlerAverage = bowlerAverage;
        this.battingStrikeRate = battingStrikeRate;
    }
}
