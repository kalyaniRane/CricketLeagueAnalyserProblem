package com.bridgelabz.cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

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
    public void givenPlayerData_WhenTopBattingAvg_ShouldReturnPlayer() throws CricketAnalyserException{
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.RUN,SortedField.BATTINGAVG,IPL_MOST_RUNS_FILE_PATH);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenTopStrikingRate_ShouldReturnPlayer() throws CricketAnalyserException{
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.RUN,SortedField.STRIKINGRATE,IPL_MOST_RUNS_FILE_PATH);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Ishant Sharma", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximum6sAnd4s_ShouldReturnPlayer() throws CricketAnalyserException{
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.RUN,SortedField.MAXIMUMHIT,IPL_MOST_RUNS_FILE_PATH);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximum6sAnd4sWithStrikeRate_ShouldReturnPlayer() throws CricketAnalyserException{
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.RUN,SortedField.STRIKERATE6S4S,IPL_MOST_RUNS_FILE_PATH);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenGreatAvgWithStrikeRate_ShouldReturnPlayer() throws CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.RUN,SortedField.AVGWITHSTRIKE,IPL_MOST_RUNS_FILE_PATH);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximumRunsWithBestAverage_ShouldReturnPlayer() throws CricketAnalyserException{
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.RUN,SortedField.MAXRUNSWITHAVG,IPL_MOST_RUNS_FILE_PATH);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestBowlingAverage_ShouldReturnPlayer() throws CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.WKT,SortedField.BOWLINGAVG,IPL_MOST_WKTS_FILE_PATH);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestStrikingRate_ShouldReturnPlayer() throws CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.WKT,SortedField.STRIKINGRATE,IPL_MOST_WKTS_FILE_PATH);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestEconomyRate_ShouldReturnPlayer() throws CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.WKT,SortedField.ECONOMYRATE,IPL_MOST_WKTS_FILE_PATH);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Ben Cutting", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenTopStrikeRateWith4w5w_ShouldReturnPlayer() throws CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.WKT,SortedField.STRIKERATE4W5W,IPL_MOST_WKTS_FILE_PATH);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Lasith Malinga", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestBowlingAvgWithStrikeRate_ShouldReturnPlayer() throws IOException, CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.WKT,SortedField.BOWLINGAVGWITHSTRIKERATE,IPL_MOST_WKTS_FILE_PATH);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximumWktsWithBestBowlingAvg_ShouldReturnPlayer() throws IOException, CricketAnalyserException {
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(CricketLeagueAnalyser.CricketData.WKT,SortedField.MAXWKTSWITHBOWLINGAVG,IPL_MOST_WKTS_FILE_PATH);
        System.out.println(sortedCricketData);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Imran Tahir", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenTopBattingAvgAndTopBowlingAvg_ShouldReturnPlayer() throws CricketAnalyserException {
        String cricketersDataInJson=cricketLeagueAnalyser.cricketAnalyseData(SortedField.ALLROUNDERAVG,IPL_MOST_RUNS_FILE_PATH, IPL_MOST_WKTS_FILE_PATH);
        IPLAllRoundersDTO[] iplAllRoundersDTOS = new Gson().fromJson(cricketersDataInJson, IPLAllRoundersDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplAllRoundersDTOS[0].player);
    }

    @Test
    public void givenPlayerData_WhenPlayerAllRounder_ShouldReturnPlayer() throws CricketAnalyserException {
        String cricketersDataInJson=cricketLeagueAnalyser.cricketAnalyseData(SortedField.ALLROUNDER,IPL_MOST_RUNS_FILE_PATH, IPL_MOST_WKTS_FILE_PATH);
        IPLAllRoundersDTO[] iplAllRoundersDTOS = new Gson().fromJson(cricketersDataInJson, IPLAllRoundersDTO[].class);
        Assert.assertEquals("Hardik Pandya", iplAllRoundersDTOS[0].player);
    }

}
