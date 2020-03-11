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

    List<IPLDTO> cricketCSVList=null;
    Map<SortedField, Comparator <IPLDTO>> sortMap= null;
    Map<String, IPLDTO> cricketMap=null;

    public CricketLeagueAnalyser() {
        this.cricketMap=new HashMap<>();
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
    }

    public String loadRunData(String csvFilePath,SortedField sortedField) throws CricketAnalyserException, IOException {

        cricketMap=new CricketLeagueLoader().loadCricketData(csvFilePath,MostRunCsv.class);
        if (cricketMap == null || cricketMap.size() == 0) {
            throw new CricketAnalyserException("no data", CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        cricketCSVList = cricketMap.values().stream().collect(Collectors.toList());
        List sortedList=sortList(sortedField, cricketCSVList);
        String sortedStateCensus = new Gson().toJson(sortedList);
        return sortedStateCensus;
    }

    public String loadWktData(String csvFilePath,SortedField sortedField) throws CricketAnalyserException, IOException {

        cricketMap=new CricketLeagueLoader().loadCricketData(csvFilePath,MostWktsCsv.class);
        if (cricketMap == null || cricketMap.size() == 0) {
            throw new CricketAnalyserException("no data", CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        cricketCSVList = cricketMap.values().stream().collect(Collectors.toList());
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
