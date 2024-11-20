package br.com.fiap.fintech.exception;

public class DBEexception extends Exception{

    public DBEexception(String message, Throwable cause) {
        super(message, cause);
    }

    public DBEexception() {
    }

    public DBEexception(String message) {
        super(message);
    }

    public DBEexception(Throwable cause) {
        super(cause);
    }

    public DBEexception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
