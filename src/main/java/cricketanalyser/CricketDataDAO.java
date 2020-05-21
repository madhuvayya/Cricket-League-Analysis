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
    public double economy;
    public int wickets;

    public CricketDataDAO(IPLMostRunsCSV mostRunsCSV) {
        this.player = mostRunsCSV.player;
        this.matches = mostRunsCSV.matches;
        this.innings = mostRunsCSV.innings;
        this.notOuts = mostRunsCSV.notOuts;
        this.runs = mostRunsCSV.runs;
        this.highScore = mostRunsCSV.highScore;
        this.average = mostRunsCSV.average;
        this.ballsFaced = mostRunsCSV.ballsFaced;
        this.strikeRate = mostRunsCSV.strikeRate;
        this.hundreds = mostRunsCSV.hundreds;
        this.fifties = mostRunsCSV.fifties;
        this.fours = mostRunsCSV.fours;
        this.sixes = mostRunsCSV.sixes;
    }

    public CricketDataDAO(IPLMostWicketsCSV mostWicketsCSV) {
        this.player = mostWicketsCSV.player;
        this.matches = mostWicketsCSV.mat;
        this.innings = mostWicketsCSV.innings;
        this.runs = mostWicketsCSV.runs;
        this.economy = mostWicketsCSV.economy;
        this.average = mostWicketsCSV.average;
        this.strikeRate = mostWicketsCSV.strikeRate;
        this.fourWickets = mostWicketsCSV.fourWickets;
        this.fiveWickets = mostWicketsCSV.fiveWickets;
    }

    public CricketDataDAO() {
    }
}
