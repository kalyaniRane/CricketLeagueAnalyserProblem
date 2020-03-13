package com.bridgelabz.cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CricketLeagueAnalyserTest
{

    private static CricketLeagueAnalyser cricketLeagueAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_WRONG_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns1.csv";
    private static final String IPL_MOST_WKTS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static CricketLeagueAnalyserFactory cricketLeagueAnalyserFactory;
    private static List<IPLDTO> cricketList;


    @BeforeClass
    public static void setUp()throws Exception{
        cricketLeagueAnalyser =new CricketLeagueAnalyser();
        cricketLeagueAnalyserFactory = new CricketLeagueAnalyserFactory();
        cricketList=new ArrayList<>();
    }

    @Test
    public void givenPlayerData_WhenTopBattingAvg_ShouldReturnPlayer(){
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.RUN, IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.BATTINGAVG,cricketList);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenTopStrikingRate_ShouldReturnPlayer(){
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.RUN, IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.STRIKINGRATE, cricketList);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Ishant Sharma", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximum6sAnd4s_ShouldReturnPlayer() {
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.RUN, IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.MAXIMUMHIT, cricketList);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximum6sAnd4sWithStrikeRate_ShouldReturnPlayer(){
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.RUN, IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.STRIKERATE6S4S,cricketList);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("Andre Russell", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenGreatAvgWithStrikeRate_ShouldReturnPlayer() {
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.RUN, IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.AVGWITHSTRIKE,cricketList);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximumRunsWithBestAverage_ShouldReturnPlayer() {
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.RUN, IPL_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.MAXRUNSWITHAVG,cricketList);
        MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
        Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestBowlingAverage_ShouldReturnPlayer() {
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.WKT, IPL_MOST_WKTS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.BOWLINGAVG,cricketList);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestStrikingRate_ShouldReturnPlayer() {
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.WKT, IPL_MOST_WKTS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.STRIKINGRATE,cricketList);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestEconomyRate_ShouldReturnPlayer(){
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.WKT, IPL_MOST_WKTS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.ECONOMYRATE,cricketList);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Ben Cutting", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenTopStrikeRateWith4w5w_ShouldReturnPlayer(){
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.WKT, IPL_MOST_WKTS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.STRIKERATE4W5W,cricketList);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Lasith Malinga", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenBestBowlingAvgWithStrikeRate_ShouldReturnPlayer(){
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.WKT, IPL_MOST_WKTS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.BOWLINGAVGWITHSTRIKERATE,cricketList);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Krishnappa Gowtham", mostWktsCsv[0].player);
    }

    @Test
    public void givenPlayerData_WhenMaximumWktsWithBestBowlingAvg_ShouldReturnPlayer() {
        cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.WKT, IPL_MOST_WKTS_FILE_PATH);
        String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.MAXWKTSWITHBOWLINGAVG,cricketList);
        MostWktsCsv[] mostWktsCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostWktsCsv[].class);
        Assert.assertEquals("Imran Tahir", mostWktsCsv[0].player);
    }

    @Test
   public void givenPlayerData_WhenTopBattingAvgAndTopBowlingAvg_ShouldReturnPlayer() {
        String cricketersDataInJson=cricketLeagueAnalyser.cricketAnalyseData(SortedField.ALLROUNDERAVG,IPL_MOST_RUNS_FILE_PATH, IPL_MOST_WKTS_FILE_PATH);
        IPLAllRoundersDTO[] iplAllRoundersDTOS = new Gson().fromJson(cricketersDataInJson, IPLAllRoundersDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham", iplAllRoundersDTOS[0].player);
    }

    @Test
    public void givenPlayerData_WhenPlayerAllRounder_ShouldReturnPlayer() {
        String cricketersDataInJson=cricketLeagueAnalyser.cricketAnalyseData(SortedField.ALLROUNDER,IPL_MOST_RUNS_FILE_PATH, IPL_MOST_WKTS_FILE_PATH);
        IPLAllRoundersDTO[] iplAllRoundersDTOS = new Gson().fromJson(cricketersDataInJson, IPLAllRoundersDTO[].class);
        Assert.assertEquals("Hardik Pandya", iplAllRoundersDTOS[0].player);
   }

    @Test
    public void givenPlayerData_WhenWrongFile_ShouldReturnException() {
        try {
            cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.RUN, IPL_WRONG_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.BATTINGAVG, cricketList);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
            Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
        }catch (CricketAnalyserException e){
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_NOT_FOUND,e.type);
        }
    }

    @Test
    public void givenPlayerData_WhenWrongClass_ShouldReturnException() {
        try {
            cricketList = cricketLeagueAnalyserFactory.getCricketAdapter(CricketLeagueAnalyser.CricketData.WKT, IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.cricketAnalyseData(SortedField.BATTINGAVG, cricketList);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(String.valueOf(sortedCricketData), MostRunCsv[].class);
            Assert.assertEquals("MS Dhoni", mostRunCsv[0].player);
        }catch (CricketAnalyserException e){
            Assert.assertEquals(CricketAnalyserException.ExceptionType.CLASS_NOT_FOUND,e.type);
        }
    }

}
