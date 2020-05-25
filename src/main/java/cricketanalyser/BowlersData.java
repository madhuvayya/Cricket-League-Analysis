package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class BowlersData {

    @CsvBindByName(column = "POS")
    public int POS;

    @CsvBindByName(column = "PLAYER",required = true)
    public String player;

    @CsvBindByName(column = "Mat")
    public int mat;

    @CsvBindByName(column = "Inns")
    public int innings;

    @CsvBindByName(column = "Ov")
    public double overs;

    @CsvBindByName(column = "Runs")
    public int runs;

    @CsvBindByName(column = "Wkts",required = true)
    public int wickets;

    @CsvBindByName(column = "BBI")
    public int bbi;

    @CsvBindByName(column = "Avg",required = true)
    public double bowlingAverage;

    @CsvBindByName(column = "Econ",required = true)
    public double economy;

    @CsvBindByName(column = "SR",required = true)
    public double bowlingStrikeRate;

    @CsvBindByName(column = "4w",required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w",required = true)
    public int fiveWickets;

    public BowlersData() {
    }

    public BowlersData(String player, int wickets, double bowlingAverage, double economy, double bowlingStrikeRate,
                       int fourWickets, int fiveWickets) {
        this.player = player;
        this.wickets = wickets;
        this.bowlingAverage = bowlingAverage;
        this.economy = economy;
        this.bowlingStrikeRate = bowlingStrikeRate;
        this.fourWickets = fourWickets;
        this.fiveWickets = fiveWickets;
    }
}
