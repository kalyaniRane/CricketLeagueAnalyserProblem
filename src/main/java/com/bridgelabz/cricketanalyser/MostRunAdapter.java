package com.bridgelabz.cricketanalyser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MostRunAdapter extends CricketLeagueAnalyserAdapter {

    public List<IPLDTO> cricketList;

    public <E> List<IPLDTO> loadData(Class<E> cricketClass, String csvFilePath) {

        cricketList = super.loadCricketData(cricketClass,csvFilePath);
        return cricketList;

    }
}
