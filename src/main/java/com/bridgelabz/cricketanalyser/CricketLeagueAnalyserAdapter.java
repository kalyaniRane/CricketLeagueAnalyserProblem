package com.bridgelabz.cricketanalyser;

import com.bridgelabz.CSVBuilderFactory;
import com.bridgelabz.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyserAdapter {
    Map<String, IPLDTO> cricketMap = new HashMap<>();
    public <E>Map<String, IPLDTO> loadCricketData(String csvFilePath, Class<E> cricketClass) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> CSVStateIterator = csvBuilder.getCSVFileIterator(reader, cricketClass);
            Iterable<E> csvIterable = () -> CSVStateIterator;

            if (cricketClass.getName().equals("com.bridgelabz.cricketanalyser.MostRunCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(MostRunCsv.class::cast)
                        .forEach(loadData -> cricketMap.put(loadData.player, new IPLDTO(loadData)));
            } else if (cricketClass.getName().equals("com.bridgelabz.cricketanalyser.MostWktsCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(MostWktsCsv.class::cast)
                        .forEach(loadData -> cricketMap.put(loadData.player, new IPLDTO(loadData)));
            }


//            StreamSupport.stream(csvIterable.spliterator(), false)
//                    .map(MostRunCsv.class::cast)
//                    .forEach(mostRunCsv -> cricketMap.put(mostRunCsv.player, new IPLDTO(mostRunCsv)));

            return cricketMap;
        }
    }
}
