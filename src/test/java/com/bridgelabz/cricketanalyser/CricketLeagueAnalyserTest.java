package com.bridgelabz.cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class CricketLeagueAnalyserTest
{

    private static CricketLeagueAnalyser cricketLeagueAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @BeforeClass
    public static void setUp()throws Exception{
        cricketLeagueAnalyser =new CricketLeagueAnalyser();
    }

    @Test
    public void givenPlayerData_WhenTopBattingAvg_ShouldReturnPlayer() throws CricketAnalyserException, IOException {
        String sortedCricketData = cricketLeagueAnalyser.loadCricketData(IPL_MOST_RUNS_FILE_PATH,SortedField.BATTINGAVG);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenTopStrikingRate_ShouldReturnPlayer() throws CricketAnalyserException, IOException {
        String sortedCricketData = cricketLeagueAnalyser.loadCricketData(IPL_MOST_RUNS_FILE_PATH,SortedField.STRIKINGRATE);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Ishant Sharma", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximum6sAnd4s_ShouldReturnPlayer() throws CricketAnalyserException, IOException {
        String sortedCricketData = cricketLeagueAnalyser.loadCricketData(IPL_MOST_RUNS_FILE_PATH,SortedField.MAXIMUMHIT);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].player);
    }

}
