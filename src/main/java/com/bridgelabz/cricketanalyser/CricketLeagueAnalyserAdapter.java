package com.bridgelabz.cricketanalyser;

import com.bridgelabz.CSVBuilderFactory;
import com.bridgelabz.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyserAdapter {
    List<IPLDTO> cricketList = new ArrayList<>();
    public <E>List<IPLDTO> loadCricketData(Class<E> cricketClass, String csvFilePath){
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> CSVStateIterator = csvBuilder.getCSVFileIterator(reader, cricketClass);
            Iterable<E> csvIterable = () -> CSVStateIterator;

            if (cricketClass.getName().equals("com.bridgelabz.cricketanalyser.MostRunCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(MostRunCsv.class::cast)
                        .forEach(loadData -> cricketList.add(new IPLDTO(loadData)));
            } else if (cricketClass.getName().equals("com.bridgelabz.cricketanalyser.MostWktsCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(MostWktsCsv.class::cast)
                        .forEach(loadData -> cricketList.add(new IPLDTO(loadData)));
            }

            return cricketList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cricketList;
    }
}
