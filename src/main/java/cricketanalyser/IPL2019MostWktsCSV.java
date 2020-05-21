package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPL2019MostWktsCSV {

    @CsvBindByName(column = "POS",required = true)
    public int POS;

    @CsvBindByName(column = "PLAYER",required = true)
    public String player;

    @CsvBindByName(column = "Mat",required = true)
    public int mat;

    @CsvBindByName(column = "Inns",required = true)
    public int innings;

    @CsvBindByName(column = "Ov",required = true)
    public double overs;

    @CsvBindByName(column = "Runs",required = true)
    public int runs;

    @CsvBindByName(column = "Wkts",required = true)
    public int wickets;

    @CsvBindByName(column = "BBI",required = true)
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

    public IPL2019MostWktsCSV(int POS, String player, int mat, int innings, double overs, int runs, int wickets, int bbi, double average, double economy, double srikeRate, int fourWickets, int fiveWickets) {
        this.POS = POS;
        this.player = player;
        this.mat = mat;
        this.innings = innings;
        this.overs = overs;
        this.runs = runs;
        this.wickets = wickets;
        this.bbi = bbi;
        this.average = average;
        this.economy = economy;
        this.strikeRate = srikeRate;
        this.fourWickets = fourWickets;
        this.fiveWickets = fiveWickets;
    }
}
