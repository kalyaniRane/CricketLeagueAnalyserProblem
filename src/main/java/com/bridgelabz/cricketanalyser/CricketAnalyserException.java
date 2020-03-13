package com.bridgelabz.cricketanalyser;

import com.bridgelabz.CSVBuilderException;

public class CricketAnalyserException extends Throwable {
        enum ExceptionType {
            CRICKET_FILE_PROBLEM, CRICKET_DATA_NOT_FOUND,UNABLE_TO_PARSE;
        }

        ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
            super(message);
            this.type = type;
        }
}
