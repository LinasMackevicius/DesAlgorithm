package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WriterIntoFile {

    public static void writeStringToFile(String str, String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
