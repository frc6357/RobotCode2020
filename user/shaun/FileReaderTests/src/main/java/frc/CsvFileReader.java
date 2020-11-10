package frc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class CsvFileReader {

    public static void main(String[] args) throws IOException {
      
        String path = "FL_insurance_sample.csv";
        String line = "";

        BufferedReader reader = new BufferedReader(new FileReader(path));

        while((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            System.out.println(values[0]);

        }
    }

}