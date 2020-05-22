package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class Bowlers {

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

    @CsvBindByName(column = "Runs",required = true)
    public int runs;

    @CsvBindByName(column = "Wkts",required = true)
    public int wickets;

    @CsvBindByName(column = "BBI")
    public int bbi;

    @CsvBindByName(column = "Avg",required = true)
    public double average;

    @CsvBindByName(column = "Econ",required = true)
    public double economy;

    @CsvBindByName(column = "SR",required = true)
    public double strikeRate;

    @CsvBindByName(column = "4w",required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w",required = true)
    public int fiveWickets;

    public Bowlers(String player, int runs, int wickets, double average, double economy, double strikeRate,
                   int fourWickets, int fiveWickets) {
        this.player = player;
        this.runs = runs;
        this.wickets = wickets;
        this.average = average;
        this.economy = economy;
        this.strikeRate = strikeRate;
        this.fourWickets = fourWickets;
        this.fiveWickets = fiveWickets;
    }
}
