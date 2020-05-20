package cricketanalyser;

public class CricketDataDAO {

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

    public CricketDataDAO(IPL2019MostRunsCSV ipl2019MostRunsCSV) {
        player = ipl2019MostRunsCSV.player;
        matches = ipl2019MostRunsCSV.matches;
        innings = ipl2019MostRunsCSV.innings;
        notOuts = ipl2019MostRunsCSV.notOuts;
        runs = ipl2019MostRunsCSV.runs;
        highScore = ipl2019MostRunsCSV.highScore;
        average = ipl2019MostRunsCSV.average;
        ballsFaced = ipl2019MostRunsCSV.ballsFaced;
        strikeRate = ipl2019MostRunsCSV.strikeRate;
        hundreds = ipl2019MostRunsCSV.hundreds;
        fifties = ipl2019MostRunsCSV.fifties;
        fours = ipl2019MostRunsCSV.fours;
        sixes = ipl2019MostRunsCSV.sixes;
    }

    public IPL2019MostRunsCSV getCricketDataDTO() {
        return new IPL2019MostRunsCSV(player, (double) average, (double) strikeRate);
    }
}
