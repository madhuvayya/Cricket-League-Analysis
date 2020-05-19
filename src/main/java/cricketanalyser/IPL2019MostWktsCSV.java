package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPL2019MostWktsCSV {

    @CsvBindByName(column = "POS",required = true)
    public int POS;

    @CsvBindByName(column = "PLAYER",required = true)
    public String PLAYER;

    @CsvBindByName(column = "Mat",required = true)
    public int Mat;

    @CsvBindByName(column = "Inns",required = true)
    public int Inns;

    @CsvBindByName(column = "Ov",required = true)
    public double Ov;

    @CsvBindByName(column = "Runs",required = true)
    public int Runs;

    @CsvBindByName(column = "Wkts",required = true)
    public int Wkts;

    @CsvBindByName(column = "BBI",required = true)
    public int BBI;

    @CsvBindByName(column = "Avg",required = true)
    public double Avg;

    @CsvBindByName(column = "Econ",required = true)
    public double Econ;

    @CsvBindByName(column = "SR",required = true)
    public double SR;

    @CsvBindByName(column = "4w",required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w",required = true)
    public int fiveWickets;

    @Override
    public String toString() {
        return "IPL2019MostWktsCSV{" +
                "POS=" + POS +
                ", PLAYER='" + PLAYER + '\'' +
                ", Mat=" + Mat +
                ", Inns=" + Inns +
                ", Ov=" + Ov +
                ", Runs=" + Runs +
                ", Wkts=" + Wkts +
                ", BBI=" + BBI +
                ", Avg=" + Avg +
                ", Econ=" + Econ +
                ", SR=" + SR +
                ", fourWickets=" + fourWickets +
                ", fiveWickets=" + fiveWickets +
                '}';
    }
}
