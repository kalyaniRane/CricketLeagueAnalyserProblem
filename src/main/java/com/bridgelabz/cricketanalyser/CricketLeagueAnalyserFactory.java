package com.bridgelabz.cricketanalyser;

import java.io.IOException;
import java.util.Map;

public class CricketLeagueAnalyserFactory {


    public <E>Map<String, IPLDTO> getCricketAdapter(CricketLeagueAnalyser.CricketData data, String csvFilePath) throws IOException {

        if (data.equals(CricketLeagueAnalyser.CricketData.RUN))
            return new MostRunAdapter().loadData(csvFilePath,MostRunCsv.class);
        else if (data.equals(CricketLeagueAnalyser.CricketData.WKT))
            return new MostWktAdapter().loadData(csvFilePath,MostWktsCsv.class);
        else
        return null;
    }
}
