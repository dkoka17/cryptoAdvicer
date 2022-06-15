package com.example.cryptoadvicer.repositories;

import com.example.cryptoadvicer.entities.AnalyzeObject;
import com.example.cryptoadvicer.entities.PriceObject;
import com.example.cryptoadvicer.entities.StatisticObject;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Component
public class CryptoAdviceRepositorieImpl {

    @Value("${spring.datasource.filename}")
    String filename;

    private HashMap<String, AnalyzeObject> analyzeObjectHashMap;


    public CryptoAdviceRepositorieImpl() {
        getAnalyzedObjectsMap();
    }

    public boolean addCrypto(String cryptoName) {

        if (writeNewCrypto(cryptoName)) {
            AnalyzeObject analyzeObject = readCrypto(cryptoName);
            analyzeObjectHashMap.put(cryptoName, analyzeObject);
            return true;
        }
        return false;
    }

    public StatisticObject getCryptoAnalyzeForDay(String cryptoName, long timestamp) {
        return analyzeObjectHashMap.get(cryptoName).getNormalizedForDay(timestamp);
    }

    public List<StatisticObject> getCryptoAnalyzeForMonth(long timestamp) {
        List<StatisticObject> list = new ArrayList<>();
        for (String crypto : analyzeObjectHashMap.keySet()) {
            StatisticObject statisticObject = analyzeObjectHashMap.get(crypto).getNormalizedForDay(timestamp);
            list.add(statisticObject);
        }

        list.sort(new Comparator<StatisticObject>() {
            @Override
            public int compare(StatisticObject o1, StatisticObject o2) {
                return (o1.getNormalize() > o1.getNormalize()) ? 1 : 0;
            }
        });

        return list;
    }


    private HashMap<String, AnalyzeObject> getAnalyzedObjectsMap() {

        analyzeObjectHashMap = new HashMap<>();

        List<String> cryptoNames = getListOfCryptos();
        for (String cryptoName : cryptoNames) {
            AnalyzeObject analyzeObject = readCrypto(cryptoName);
            analyzeObjectHashMap.put(cryptoName, analyzeObject);
        }
        return analyzeObjectHashMap;
    }

    private AnalyzeObject readCrypto(String cryptoName) {
        AnalyzeObject analyzeObject = new AnalyzeObject(cryptoName);
        try {

            File file = new ClassPathResource("static/" + cryptoName + "_values.csv").getFile();
            CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();
            try (CSVReader reader = new CSVReaderBuilder(
                    new FileReader(file))
                    .withCSVParser(csvParser)
                    .withSkipLines(1)
                    .build()) {

                List<String[]> r = reader.readAll();
                r.forEach(x -> analyzeObject.addValue(new PriceObject(x)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return analyzeObject;
    }

    private List<String> getListOfCryptos() {
        List<String> cryptoNames = new ArrayList<>();
        try {
            File file = new ClassPathResource("static/UsedCryptos.txt").getFile();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String curLine = sc.nextLine();
                System.out.println(curLine);
                cryptoNames.add(curLine);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cryptoNames;
    }

    private boolean writeNewCrypto(String cryptoName) {
        try {
            File file = new ClassPathResource("static/UsedCryptos.txt").getFile();
            FileWriter fw = new FileWriter(file, true);
            fw.write(cryptoName + "\n");//appends the string to the file
            fw.flush();
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
            return false;
        }
        return true;
    }
}
