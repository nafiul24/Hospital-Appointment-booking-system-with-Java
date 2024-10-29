package util;

import model.Hospital;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<Hospital> readHospitalsFromCSV(String filePath) {
        List<Hospital> hospitals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                hospitals.add(new Hospital(values[0], values[1], values[2], values[3]));  // Removed rating value
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hospitals;
    }
}
