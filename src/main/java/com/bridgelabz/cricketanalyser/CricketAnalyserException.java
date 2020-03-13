package com.bridgelabz.cricketanalyser;

public class CricketAnalyserException extends RuntimeException {
        enum ExceptionType {
            DATA_NOT_FOUND, FILE_NOT_FOUND,CLASS_NOT_FOUND
        }

        ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
            super(message);
            this.type = type;
        }
}
