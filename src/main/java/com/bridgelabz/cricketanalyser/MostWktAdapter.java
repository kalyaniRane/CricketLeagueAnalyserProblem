package com.bridgelabz.cricketanalyser;

import java.io.IOException;
import java.util.Map;

public class MostWktAdapter extends CricketLeagueAnalyserAdapter{
    public Map<String, IPLDTO> cricketMap;

    public <E> Map<String, IPLDTO> loadData(String csvFilePath, Class<E> cricketClass) throws IOException {

        cricketMap = super.loadCricketData(csvFilePath,cricketClass);
        return cricketMap;

    }
}
