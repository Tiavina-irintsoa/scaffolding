package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import scaffolding.View;

public class ViewReader {
    String filePath="viewConfig.json";

    public View[] read() throws Exception{
        String templateDIR = System.getenv("templateDIR");
        if(templateDIR == null){
            throw new Exception("Aucune variable d'environnement specifie pour templateDIR");
        }
        String path = templateDIR + "/" + filePath;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Gson gson = new Gson();
            View[] view = gson.fromJson(reader, View[].class);
            return view;
        } catch (IOException e) {
            throw e;
        }
    }
}
