package com.bridgelabz.cricketanalyser;

import java.util.List;

public class MostWktAdapter extends CricketLeagueAnalyserAdapter{
    public List<IPLDTO> cricketList;

    public <E> List<IPLDTO> loadData(Class<E> cricketClass, String csvFilePath) {
            cricketList = super.loadCricketData(cricketClass, csvFilePath);
            return cricketList;
    }
}
