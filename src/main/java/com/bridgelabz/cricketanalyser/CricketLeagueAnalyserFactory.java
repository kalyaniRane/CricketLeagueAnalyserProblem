package com.bridgelabz.cricketanalyser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CricketLeagueAnalyserFactory {


    public <E> List<IPLDTO> getCricketAdapter(CricketLeagueAnalyser.CricketData data, String csvFilePath) throws CricketAnalyserException {

        if (data.equals(CricketLeagueAnalyser.CricketData.RUN))
            return new MostRunAdapter().loadData(MostRunCsv.class,csvFilePath);
        else if (data.equals(CricketLeagueAnalyser.CricketData.WKT))
            return new MostWktAdapter().loadData(MostWktsCsv.class,csvFilePath);
        else
        throw new CricketAnalyserException("File not found",CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
    }
}
