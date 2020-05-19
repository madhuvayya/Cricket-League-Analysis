package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPL2019MostRunsCSV {

    @CsvBindByName(column = "POS",required = true)
    public int POS;

    @CsvBindByName(column = "PLAYER",required = true)
    public String PLAYER;

    @CsvBindByName(column = "Mat",required = true)
    public int Mat;

    @CsvBindByName(column = "Inns",required = true)
    public int Inns;

    @CsvBindByName(column = "NO",required = true)
    public double NO;

    @CsvBindByName(column = "Runs",required = true)
    public int Runs;

    @CsvBindByName(column = "Hs",required = true)
    public int HS;

    @CsvBindByName(column = "BF",required = true)
    public int BF;

    @CsvBindByName(column = "Avg",required = true)
    public double Avg;

    @CsvBindByName(column = "SR",required = true)
    public double SR;

    @CsvBindByName(column = "4s",required = true)
    public int fours;

    @CsvBindByName(column = "6s",required = true)
    public int sixes;

    @CsvBindByName(column = "100",required = true)
    public int hundreds;

    @CsvBindByName(column = "50",required = true)
    public int fifties;

    @Override
    public String toString() {
        return "IPL2019MostRunsCSV{" +
                "POS=" + POS +
                ", PLAYER='" + PLAYER + '\'' +
                ", Mat=" + Mat +
                ", Inns=" + Inns +
                ", NO=" + NO +
                ", Runs=" + Runs +
                ", HS=" + HS +
                ", BF=" + BF +
                ", Avg=" + Avg +
                ", SR=" + SR +
                ", fours=" + fours +
                ", sixes=" + sixes +
                ", hundreds=" + hundreds +
                ", fifties=" + fifties +
                '}';
    }
}
