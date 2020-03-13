package com.bridgelabz.cricketanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {

    public enum CricketData {
        RUN,WKT;
    }

    Map<String, IPLAllRoundersDTO> mapAllRounder;


    public CricketLeagueAnalyser() {
        this.mapAllRounder =new HashMap<>();
        
        SortedField.test();
    }

    public String cricketAnalyseData(SortedField sortType, String... csvFilePath) {
        try {
            List<IPLDTO> batsmanList = new CricketLeagueAnalyserFactory().getCricketAdapter(CricketData.RUN, csvFilePath[0]);
            List<IPLDTO> bowlerList = new CricketLeagueAnalyserFactory().getCricketAdapter(CricketData.WKT, csvFilePath[1]);

            for (int i = 0; i < batsmanList.size(); i++) {
                for (int j = 0; j < bowlerList.size(); j++) {
                    if (batsmanList.get(i).player.equals(bowlerList.get(j).player)) {
                        if (sortType.equals(SortedField.ALLROUNDERAVG))
                            mapAllRounder.put(batsmanList.get(i).player, new IPLAllRoundersDTO(batsmanList.get(i).player, batsmanList.get(i).battingAvg + bowlerList.get(j).bowlingAvg));
                        if (sortType.equals(SortedField.ALLROUNDER))
                            mapAllRounder.put(batsmanList.get(i).player, new IPLAllRoundersDTO(batsmanList.get(i).player, batsmanList.get(i).runs * bowlerList.get(j).maxWkts));
                    }
                }
            }
            List<IPLAllRoundersDTO> collect = mapAllRounder.values().stream().collect(Collectors.toList());
            collect.sort(SortedField.sortMapAllRounder.get(SortedField.BOWLINGAVGWITHBATTINGAVG).reversed());

            return new Gson().toJson(collect);
        }catch (CricketAnalyserException e){
            throw new CricketAnalyserException("File Not Found",CricketAnalyserException.ExceptionType.FILE_NOT_FOUND);
        }
    }

    public String cricketAnalyseData(SortedField sortedField, List<IPLDTO> cricketCSVList){
        try {
                if (cricketCSVList == null || cricketCSVList.size() == 0) {
                    throw new CricketAnalyserException("No Data Found", CricketAnalyserException.ExceptionType.DATA_NOT_FOUND);
                }

                List sortedList = sortList(sortedField, cricketCSVList);
                String sortedStateCensus = new Gson().toJson(sortedList);
                return sortedStateCensus;
            }catch (CricketAnalyserException e){
                throw new CricketAnalyserException("No Data Found",CricketAnalyserException.ExceptionType.DATA_NOT_FOUND);
            }
    }

    public List sortList(SortedField sortedField, List iplCricketersList) {
        return (List) iplCricketersList.stream()
                .sorted(SortedField.sortMap.get(sortedField).reversed())
                .collect(Collectors.toList());
    }

}
