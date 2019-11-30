package myPage.data;

public class ErrorMessage {
    Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public ErrorMessage(Exception exception){
        this.exception = exception;
    }
}
