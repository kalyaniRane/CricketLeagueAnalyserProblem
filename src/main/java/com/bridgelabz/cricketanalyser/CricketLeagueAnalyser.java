package com.bridgelabz.cricketanalyser;

import com.bridgelabz.CSVBuilderFactory;
import com.bridgelabz.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {


    private CricketData data;

    public enum CricketData {RUN,WKT}


    List<IPLDTO> cricketCSVList=null;
    Map<SortedField, Comparator <IPLDTO>> sortMap= null;

    public CricketLeagueAnalyser() {
        this.sortMap=new HashMap<>();
        this.sortMap.put(SortedField.BATTINGAVG,Comparator.comparing(cricket->cricket.battingAvg));
        this.sortMap.put(SortedField.STRIKINGRATE,Comparator.comparing(cricket->cricket.strikingRate));
        this.sortMap.put(SortedField.MAXIMUMHIT,Comparator.comparing(cricket->cricket.sixes+cricket.fours));
        Comparator<IPLDTO> sixFourComparator = Comparator.comparing(cricket -> cricket.sixes + cricket.fours);
        this.sortMap.put(SortedField.STRIKERATE6S4S,sixFourComparator.thenComparing(cricket->cricket.strikingRate));
        Comparator<IPLDTO> averageComparator = Comparator.comparing(cricket -> cricket.battingAvg);
        this.sortMap.put(SortedField.AVGWITHSTRIKE,averageComparator.thenComparing(cricket->cricket.strikingRate));
        this.sortMap.put(SortedField.MAXRUNSWITHAVG,averageComparator.thenComparing(cricket->cricket.runs));
        this.sortMap.put(SortedField.BOWLINGAVG,Comparator.comparing(cricket->cricket.bowlingAvg));
        this.sortMap.put(SortedField.STRIKINGRATE,Comparator.comparing(cricket->cricket.strikingRate));
        this.sortMap.put(SortedField.ECONOMYRATE,Comparator.comparing(cricket->cricket.economyRate));
        Comparator<IPLDTO> fourFiveWkt = Comparator.comparing(cricket->cricket.fiveWkt+cricket.fourWkt);
        this.sortMap.put(SortedField.STRIKERATE4W5W,fourFiveWkt.thenComparing(cricket -> cricket.strikingRate));
        Comparator<IPLDTO> bowlingAvg = Comparator.comparing(cricket -> cricket.bowlingAvg);
        this.sortMap.put(SortedField.BOWLINGAVGWITHSTRIKERATE,bowlingAvg.thenComparing(cricket -> cricket.strikingRate));
        Comparator<IPLDTO> maxWkts = Comparator.comparing(cricket -> cricket.maxWkts);
        this.sortMap.put(SortedField.MAXWKTSWITHBOWLINGAVG,maxWkts.thenComparing(cricket -> cricket.bowlingAvg));
    }

    public String loadData(CricketData data,String csvFilePath,SortedField sortedField) throws CricketAnalyserException{

        cricketCSVList=new CricketLeagueAnalyserFactory().getCricketAdapter(data,csvFilePath);
        if (cricketCSVList == null || cricketCSVList.size() == 0) {
            throw new CricketAnalyserException("no data", CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }

        List sortedList=sortList(sortedField, cricketCSVList);
        String sortedStateCensus = new Gson().toJson(sortedList);
        return sortedStateCensus;
    }

    public List sortList(SortedField sortedField, List iplCricketersList) {
        return (List) iplCricketersList.stream()
                .sorted(this.sortMap.get(sortedField).reversed())
                .collect(Collectors.toList());
    }

}
