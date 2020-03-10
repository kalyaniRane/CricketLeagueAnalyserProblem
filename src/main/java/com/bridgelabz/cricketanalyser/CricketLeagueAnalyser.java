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

    List<MostRunCsv> cricketCSVList=null;
    Map<SortedField, Comparator <MostRunCsv>> sortMap= null;
    Map<String, MostRunCsv> cricketMap=null;

    public CricketLeagueAnalyser() {
        this.cricketMap=new HashMap<>();
        this.sortMap=new HashMap<>();
        this.sortMap.put(SortedField.BATTINGAVG,Comparator.comparing(cricket->cricket.battingAvg));
        this.sortMap.put(SortedField.STRIKINGRATE,Comparator.comparing(cricket->cricket.strikingRate));
        this.sortMap.put(SortedField.MAXIMUMHIT,Comparator.comparing(cricket->cricket.sixes+cricket.fours));
        Comparator<MostRunCsv> sixFourComparator = Comparator.comparing(cricket -> cricket.sixes + cricket.fours);
        this.sortMap.put(SortedField.STRIKERATE6S4S,sixFourComparator.thenComparing(cricket->cricket.strikingRate));
        Comparator<MostRunCsv> averageComparator = Comparator.comparing(cricket -> cricket.battingAvg);
        this.sortMap.put(SortedField.AVGWITHSTRIKE,averageComparator.thenComparing(cricket->cricket.strikingRate));
        this.sortMap.put(SortedField.MAXRUNSWITHAVG,averageComparator.thenComparing(cricket->cricket.runs));
    }

    public String loadCricketData(String csvFilePath,SortedField sortedField) throws CricketAnalyserException, IOException {

        cricketMap=new CricketLeagueLoader().loadCricketData(csvFilePath);
        if (cricketMap == null || cricketMap.size() == 0) {
            throw new CricketAnalyserException("no census data", CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
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
