package cricketanalyser;

public class CricketAnalyserException extends Exception {

    enum ExceptionType {
        CSV_FILE_PROBLEM,
        DATA_NOT_APPROPRIATE,
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
