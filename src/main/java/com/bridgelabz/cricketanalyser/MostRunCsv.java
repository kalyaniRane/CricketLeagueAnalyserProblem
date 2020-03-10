package com.bridgelabz.cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class MostRunCsv {
    @CsvBindByName(column = "Avg",required = true)
    public double battingAvg;

    @CsvBindByName(column = "PLAYER",required = true)
    public String player;
}
