package myPage.exceptions;

public class DBReadWriteException extends Exception{
    public DBReadWriteException(){}
    public DBReadWriteException(String message) {
        super(message);
    }
}
