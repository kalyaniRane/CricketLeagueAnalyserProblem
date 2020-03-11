package com.bridgelabz.cricketanalyser;

public class IPLDTO {
    public String player;
    public double battingAvg;
    public double strikingRate;
    public int sixes;
    public int fours;
    public int runs;
    public double bowlingAvg;
    public double economyRate;
    public int fourWkt;
    public int fiveWkt;

    public IPLDTO(MostRunCsv mostRunCsv) {
        player = mostRunCsv.player;
        battingAvg = mostRunCsv.battingAvg;
        strikingRate = mostRunCsv.strikingRate;
        sixes = mostRunCsv.sixes;
        fours = mostRunCsv.fours;
        runs = mostRunCsv.runs;
    }

    public IPLDTO(MostWktsCsv mostWktsCsv) {
        player = mostWktsCsv.player;
        bowlingAvg = mostWktsCsv.bowlingAvg;
        strikingRate = mostWktsCsv.strikingRate;
        economyRate=mostWktsCsv.economyRate;
        fourWkt=mostWktsCsv.fourWkt;
        fiveWkt=mostWktsCsv.fiveWkt;
    }
}
