package myPage.exceptions;

public class NoResultsException extends DBReadWriteException{
    public NoResultsException(){}
    public NoResultsException(String message){
        super(message);
    }
}
