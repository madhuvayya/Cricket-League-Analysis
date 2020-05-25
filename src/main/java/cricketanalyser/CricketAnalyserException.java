package cricketanalyser;

public class CricketAnalyserException extends Exception {

    enum ExceptionType {
        FILE_PROBLEM,
        NO_DATA
    }
    ExceptionType type;

    public CricketAnalyserException(String message,ExceptionType type) {
        super(message);
        this.type = type;
    }
}
