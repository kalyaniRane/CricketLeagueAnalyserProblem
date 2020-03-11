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
    private static final String IPL_MOST_WKTS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @BeforeClass
    public static void setUp()throws Exception{
        cricketLeagueAnalyser =new CricketLeagueAnalyser();
    }

    @Test
    public void givenPlayerData_WhenTopBattingAvg_ShouldReturnPlayer() throws CricketAnalyserException, IOException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.RUN,IPL_MOST_RUNS_FILE_PATH,SortedField.BATTINGAVG);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenTopStrikingRate_ShouldReturnPlayer() throws CricketAnalyserException, IOException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.RUN,IPL_MOST_RUNS_FILE_PATH,SortedField.STRIKINGRATE);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Ishant Sharma", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximum6sAnd4s_ShouldReturnPlayer() throws CricketAnalyserException, IOException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.RUN,IPL_MOST_RUNS_FILE_PATH,SortedField.MAXIMUMHIT);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximum6sAnd4sWithStrikeRate_ShouldReturnPlayer() throws CricketAnalyserException, IOException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.RUN,IPL_MOST_RUNS_FILE_PATH,SortedField.STRIKERATE6S4S);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenGreatAvgWithStrikeRate_ShouldReturnPlayer() throws CricketAnalyserException, IOException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.RUN,IPL_MOST_RUNS_FILE_PATH,SortedField.AVGWITHSTRIKE);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximumRunsWithBestAverage_ShouldReturnPlayer() throws CricketAnalyserException, IOException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.RUN,IPL_MOST_RUNS_FILE_PATH,SortedField.MAXRUNSWITHAVG);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestBowlingAverage_ShouldReturnPlayer() throws IOException, CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.WKT,IPL_MOST_WKTS_FILE_PATH,SortedField.BOWLINGAVG);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestStrikingRate_ShouldReturnPlayer() throws IOException, CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.WKT,IPL_MOST_WKTS_FILE_PATH,SortedField.STRIKINGRATE);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestEconomyRate_ShouldReturnPlayer() throws IOException, CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.WKT,IPL_MOST_WKTS_FILE_PATH,SortedField.ECONOMYRATE);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Ben Cutting", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenTopStrikeRateWith4w5w_ShouldReturnPlayer() throws IOException, CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.WKT,IPL_MOST_WKTS_FILE_PATH,SortedField.STRIKERATE4W5W);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Lasith Malinga", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestBowlingAvgWithStrikeRate_ShouldReturnPlayer() throws IOException, CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.loadData(CricketLeagueAnalyser.CricketData.WKT,IPL_MOST_WKTS_FILE_PATH,SortedField.BOWLINGAVGWITHSTRIKERATE);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWktsCsv[0].player);
    }


}
