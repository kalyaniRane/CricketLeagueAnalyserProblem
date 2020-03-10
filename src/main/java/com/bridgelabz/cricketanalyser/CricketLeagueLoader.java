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

public class CricketLeagueLoader {

    public Map<String, MostRunCsv> loadCricketData(String csvFilePath) throws CricketAnalyserException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<MostRunCsv> CSVStateIterator = csvBuilder.getCSVFileIterator(reader, MostRunCsv.class);
            Iterable<MostRunCsv> csvIterable = () -> CSVStateIterator;
            Map<String, MostRunCsv> cricketMap = new HashMap<>();
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(MostRunCsv.class::cast)
                    .forEach(mostRunCsv -> cricketMap.put(mostRunCsv.player, new IPLDTO(mostRunCsv)));

            return cricketMap;
        }
    }

}
