package com.bridgelabz.cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class MostRunCsv {
    @CsvBindByName(column = "Avg",required = true)
    public double battingAvg;

    @CsvBindByName(column = "PLAYER",required = true)
    public String player;

    @CsvBindByName(column = "SR",required = true)
    public double strikingRate;

    @CsvBindByName(column = "6s",required = true)
    public int sixes;

    @CsvBindByName(column = "4s",required = true)
    public int fours;
}
