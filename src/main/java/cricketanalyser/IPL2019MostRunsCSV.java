package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPL2019MostRunsCSV {

    @CsvBindByName(column = "POS",required = true)
    public int POS;

    @CsvBindByName(column = "PLAYER",required = true)
    public String player;

    @CsvBindByName(column = "Mat",required = true)
    public int matches;

    @CsvBindByName(column = "Inns",required = true)
    public int innings;

    @CsvBindByName(column = "NO",required = true)
    public double notOuts;

    @CsvBindByName(column = "Runs",required = true)
    public int runs;

    @CsvBindByName(column = "Hs",required = true)
    public int highScore;

    @CsvBindByName(column = "BF",required = true)
    public int ballsFaced;

    @CsvBindByName(column = "Avg",required = true)
    public double average;

    @CsvBindByName(column = "SR",required = true)
    public double strikeRate;

    @CsvBindByName(column = "4s",required = true)
    public int fours;

    @CsvBindByName(column = "6s",required = true)
    public int sixes;

    @CsvBindByName(column = "100",required = true)
    public int hundreds;

    @CsvBindByName(column = "50",required = true)
    public int fifties;

    public IPL2019MostRunsCSV(String player, double average) {
        this.average = average;
        this.player = player;
    }
}
