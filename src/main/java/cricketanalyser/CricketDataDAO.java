package cricketanalyser;

public class CricketDataDAO {

    public int fiveWickets;
    public int fourWickets;
    public String player;
    public int matches;
    public int innings;
    public int notOuts;
    public int runs;
    public int highScore;
    public double average;
    public int ballsFaced;
    public double strikeRate;
    public int hundreds;
    public int fifties;
    public int fours;
    public int sixes;

    public CricketDataDAO(IPLMostRunsCSV ipl2019MostRunsCSV) {
        this.player = ipl2019MostRunsCSV.player;
        this.matches = ipl2019MostRunsCSV.matches;
        this.innings = ipl2019MostRunsCSV.innings;
        this.notOuts = ipl2019MostRunsCSV.notOuts;
        this.runs = ipl2019MostRunsCSV.runs;
        this.highScore = ipl2019MostRunsCSV.highScore;
        this.average = ipl2019MostRunsCSV.average;
        this.ballsFaced = ipl2019MostRunsCSV.ballsFaced;
        this.strikeRate = ipl2019MostRunsCSV.strikeRate;
        this.hundreds = ipl2019MostRunsCSV.hundreds;
        this.fifties = ipl2019MostRunsCSV.fifties;
        this.fours = ipl2019MostRunsCSV.fours;
        this.sixes = ipl2019MostRunsCSV.sixes;
    }

    public CricketDataDAO(IPL2019MostWktsCSV ipl2019MostWktsCSV) {
        this.player = ipl2019MostWktsCSV.player;
        this.matches = ipl2019MostWktsCSV.mat;
        this.innings = ipl2019MostWktsCSV.innings;
        this.runs = ipl2019MostWktsCSV.runs;
        this.average = ipl2019MostWktsCSV.average;
        this.strikeRate = ipl2019MostWktsCSV.strikeRate;
        this.fourWickets = ipl2019MostWktsCSV.fourWickets;
        this.fiveWickets = ipl2019MostWktsCSV.fiveWickets;
    }

    public CricketDataDAO() {
    }
}
