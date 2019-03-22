package controllers.Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetterConfig {
    String[] parts;

    public GetterConfig(String address) {
        try {
            System.out.println(address);
            ReadConfig(address);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void ReadConfig(String address) throws FileNotFoundException {
        try {
            File f = new File(address);
            Scanner reader = new Scanner(f);
            System.out.println(reader);
            while (reader.hasNextLine()) {
                String line = reader.next();
                System.out.println(line);
                parts = line.split(",");
            }
        } catch (FileNotFoundException e) {
            System.out.println(" File not found");
        }
    }

    public GetterConfig(String[] parts) {
        this.parts = parts;
    }

    public String getParts(int pos) {
        return parts[pos];
    }
}
