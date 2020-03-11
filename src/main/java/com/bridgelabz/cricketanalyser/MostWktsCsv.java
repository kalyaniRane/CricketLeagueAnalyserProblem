package com.bridgelabz.cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class MostWktsCsv {

    @CsvBindByName(column = "PLAYER",required = true)
    public String player;

    @CsvBindByName(column = "Avg",required = true)
    public double bowlingAvg;

    @CsvBindByName(column = "SR",required = true)
    public double strikingRate;

    @CsvBindByName(column = "Econ",required = true)
    public double economyRate;

    @CsvBindByName(column = "4w",required = true)
    public int fourWkt;

    @CsvBindByName(column = "5w",required = true)
    public int fiveWkt;

    @CsvBindByName(column = "Wkts",required = true)
    public int maxWkts;


}
