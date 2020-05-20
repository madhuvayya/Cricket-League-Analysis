package cricketanalyser;

public class IPLDataDAO {

    public String player;
    public int matches;
    public int innings;
    public int notOuts;
    public int runs;
    public int highestScore;
    public double average;
    public int ballsFaced;
    public double strikeRate;
    public int hundreds;
    public int fifties;
    public int fours;
    public int sixes;

    public IPLDataDAO(IPL2019MostRunsCSV ipl2019MostRunsCSV) {
        player = player;
        matches = matches;
        innings = innings;
        notOuts = notOuts;
        runs = runs;
        highestScore = highestScore;
        average = average;
        ballsFaced = ballsFaced;
        strikeRate = strikeRate;
        hundreds = hundreds;
        fifties = fifties;
        fours = fours;
        sixes = sixes;
    }

    public IPL2019MostRunsCSV getIPLDataDTO() {
        return new IPL2019MostRunsCSV(player, (double) average);
    }
}
