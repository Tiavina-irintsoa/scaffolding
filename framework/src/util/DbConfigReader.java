package util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import scaffolding.DBConfig;

public class DbConfigReader {
    String filePath="dbconfigs.json";
    public DBConfig read() throws Exception{
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Gson gson = new Gson();
            DBConfig dbconfig = gson.fromJson(reader, DBConfig.class);
            return dbconfig;
        } catch (IOException e) {
            throw e;
        }
    }
}
