package cricketanalyser;

public class CricketAnalyserException extends Exception {

    enum ExceptionType {
        FILE_PROBLEM,
        DATA_NOT_APPROPRIATE,
        NO_DATA
    }
    ExceptionType type;

    public CricketAnalyserException(String message,ExceptionType type) {
        super(message);
        this.type = type;
    }
}
