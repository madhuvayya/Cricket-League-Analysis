package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class BatsMen {

    @CsvBindByName(column = "POS")
    public int POS;

    @CsvBindByName(column = "PLAYER",required = true)
    public String player;

    @CsvBindByName(column = "Mat")
    public int matches;

    @CsvBindByName(column = "Inns")
    public int innings;

    @CsvBindByName(column = "NO")
    public int notOuts;

    @CsvBindByName(column = "Runs",required = true)
    public int runs;

    @CsvBindByName(column = "Hs")
    public int highScore;

    @CsvBindByName(column = "BF")
    public int ballsFaced;

    @CsvBindByName(column = "Avg",required = true)
    public double average;

    @CsvBindByName(column = "SR",required = true)
    public double strikeRate;

    @CsvBindByName(column = "4s",required = true)
    public int fours;

    @CsvBindByName(column = "6s",required = true)
    public int sixes;

    @CsvBindByName(column = "100")
    public int hundreds;

    @CsvBindByName(column = "50")
    public int fifties;

    public BatsMen(String player, int runs, double average, double strikeRate, int fours, int sixes) {
        this.player = player;
        this.runs = runs;
        this.average = average;
        this.strikeRate = strikeRate;
        this.fours = fours;
        this.sixes = sixes;
    }
}
