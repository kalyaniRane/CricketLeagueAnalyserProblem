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
    }

    public String loadCricketData(String csvFilePath,SortedField sortedField) throws CricketAnalyserException, IOException {

        cricketMap=new CricketLeagueLoader().loadCricketData(csvFilePath);
        if (cricketMap == null || cricketMap.size() == 0) {
            throw new CricketAnalyserException("no census data", CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        cricketCSVList = cricketMap.values().stream().collect(Collectors.toList());
        this.sort(this.sortMap.get(sortedField).reversed());
        String sortedStateCensus = new Gson().toJson(this.cricketCSVList);
        return sortedStateCensus;
    }

    private void sort(Comparator<MostRunCsv> censusComparator) {
        for(int i=0;i<cricketCSVList.size()-1;i++){
            for(int j=0;j<cricketCSVList.size()-i-1;j++){
                MostRunCsv run1 = cricketCSVList.get(j);
                MostRunCsv run2 = cricketCSVList.get(j+1);
                if(censusComparator.compare(run1,run2)>0){
                    cricketCSVList.set(j,run2);
                    cricketCSVList.set(j+1,run1);
                }
            }
        }
    }
}
