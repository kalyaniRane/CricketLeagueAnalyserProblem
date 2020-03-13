package com.bridgelabz.cricketanalyser;

import com.bridgelabz.CSVBuilderException;
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
    Map<String, IPLAllRoundersDTO> mapTest;
    Map<SortedField, Comparator<IPLAllRoundersDTO>> sortMapAllRounder;
    public CricketLeagueAnalyser() {
        this.sortMap=new HashMap<>();
        this.mapTest=new HashMap<>();
        this.sortMapAllRounder=new HashMap<>();
        this.sortMapAllRounder.put(SortedField.AVG, Comparator.comparing(iplData -> iplData.average));
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
        this.sortMap.put(SortedField.BOWLINGAVGWITHBATTINGAVG,Comparator.comparing(IPLDTO -> IPLDTO.bowlingAvg+IPLDTO.battingAvg));
    }

    public String cricketAnalyseData(SortedField sortType, String... csvFilePath) {
        List<IPLDTO> batsmanList = new CricketLeagueAnalyserFactory().getCricketAdapter(CricketData.RUN,csvFilePath[0]);
        List<IPLDTO> bowlerList = new CricketLeagueAnalyserFactory().getCricketAdapter(CricketData.WKT,csvFilePath[1]);

        for (int i = 0; i < batsmanList.size(); i++) {
            for (int j = 0; j < bowlerList.size(); j++) {
                if (batsmanList.get(i).player.equals(bowlerList.get(j).player)) {
                    if (sortType.equals(SortedField.ALLROUNDERAVG))
                        mapTest.put(batsmanList.get(i).player, new IPLAllRoundersDTO(batsmanList.get(i).player,batsmanList.get(i).battingAvg + bowlerList.get(j).bowlingAvg));
                }
            }
        }
        List<IPLAllRoundersDTO> collect = mapTest.values().stream().collect(Collectors.toList());
        collect.sort(this.sortMapAllRounder.get(SortedField.AVG).reversed());

        return new Gson().toJson(collect);
    }

    public String cricketAnalyseData(CricketData data, SortedField sortedField, String csvFilePath) throws CricketAnalyserException{

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
