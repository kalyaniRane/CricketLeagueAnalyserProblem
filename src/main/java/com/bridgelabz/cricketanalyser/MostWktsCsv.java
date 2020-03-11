package com.bridgelabz.cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class MostWktsCsv {

    @CsvBindByName(column = "PLAYER",required = true)
    public String player;

    @CsvBindByName(column = "Avg",required = true)
    public double bowlingAvg;
}
