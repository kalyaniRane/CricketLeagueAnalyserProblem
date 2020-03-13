package com.bridgelabz.cricketanalyser;

import javax.management.openmbean.TabularData;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum SortedField {
    BATTINGAVG, MAXIMUMHIT, STRIKERATE6S4S, AVGWITHSTRIKE, MAXRUNS, MAXRUNSWITHAVG, STRIKINGRATE,
    ECONOMYRATE, STRIKERATE4W5W, BOWLINGAVGWITHSTRIKERATE, MAXWKTSWITHBOWLINGAVG, BOWLINGAVGWITHBATTINGAVG, ALLROUNDERAVG, ALLROUNDER, BOWLINGAVG;


    public static Map<SortedField, Comparator<IPLAllRoundersDTO>> sortMapAllRounder=new HashMap<>();
    public static Map<SortedField, Comparator <IPLDTO>> sortMap=new HashMap<>();

    public static void test()
    {
        sortMapAllRounder.put(SortedField.BOWLINGAVGWITHBATTINGAVG, Comparator.comparing(iplData -> iplData.average));
        sortMap.put(SortedField.BATTINGAVG,Comparator.comparing(cricket->cricket.battingAvg));
        sortMap.put(SortedField.STRIKINGRATE,Comparator.comparing(cricket->cricket.strikingRate));
        sortMap.put(SortedField.MAXIMUMHIT,Comparator.comparing(cricket->cricket.sixes+cricket.fours));
        Comparator<IPLDTO> sixFourComparator = Comparator.comparing(cricket -> cricket.sixes + cricket.fours);
        sortMap.put(SortedField.STRIKERATE6S4S,sixFourComparator.thenComparing(cricket->cricket.strikingRate));
        Comparator<IPLDTO> averageComparator = Comparator.comparing(cricket -> cricket.battingAvg);
        sortMap.put(SortedField.AVGWITHSTRIKE,averageComparator.thenComparing(cricket->cricket.strikingRate));
        sortMap.put(SortedField.MAXRUNSWITHAVG,averageComparator.thenComparing(cricket->cricket.runs));
        sortMap.put(SortedField.BOWLINGAVG,Comparator.comparing(cricket->cricket.bowlingAvg));
        sortMap.put(SortedField.STRIKINGRATE,Comparator.comparing(cricket->cricket.strikingRate));
        sortMap.put(SortedField.ECONOMYRATE,Comparator.comparing(cricket->cricket.economyRate));
        Comparator<IPLDTO> fourFiveWkt = Comparator.comparing(cricket->cricket.fiveWkt+cricket.fourWkt);
        sortMap.put(SortedField.STRIKERATE4W5W,fourFiveWkt.thenComparing(cricket -> cricket.strikingRate));
        Comparator<IPLDTO> bowlingAvg = Comparator.comparing(cricket -> cricket.bowlingAvg);
        sortMap.put(SortedField.BOWLINGAVGWITHSTRIKERATE,bowlingAvg.thenComparing(cricket -> cricket.strikingRate));
        Comparator<IPLDTO> maxWkts = Comparator.comparing(cricket -> cricket.maxWkts);
        sortMap.put(SortedField.MAXWKTSWITHBOWLINGAVG,maxWkts.thenComparing(cricket -> cricket.bowlingAvg));
    }

}
