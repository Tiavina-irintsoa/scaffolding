package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import scaffolding.Langage;
import com.google.gson.Gson;

public class LangageReader {
    
    String filePath="langageConfig.json";

    public Langage[] read() throws Exception{
        String templateDIR = System.getenv("templateDIR");
        if(templateDIR == null){
            throw new Exception("Aucune variable d'environnement specifie pour templateDIR");
        }
        String path = templateDIR + "/" + filePath;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Gson gson = new Gson();
            Langage[] langage = gson.fromJson(reader, Langage[].class);
            return langage;
        } catch (IOException e) {
            throw e;
        }
    }

}
