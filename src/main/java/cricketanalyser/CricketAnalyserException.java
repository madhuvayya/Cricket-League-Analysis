package cricketanalyser;

public class CricketAnalyserException extends Exception {

    enum ExceptionType {
        IPL_FILE_PROBLEM,
        NO_CENSUS_DATA
    }

    ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CricketAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    public CricketAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }
}
