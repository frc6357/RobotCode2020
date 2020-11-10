package frc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class TxtFileReader {

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File("C:/Users/Public/practice text file.txt");
        Scanner scan = new Scanner(file);

        while(scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }

        scan.close();

    }

}