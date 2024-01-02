package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static void createAndWriteModel(String fileName, String content) throws IOException{
        File fichier = new File("models/"+fileName);
        FileWriter fileWriter = new FileWriter(fichier);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(content);
        writer.close();
    }
}
