package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import scaffolding.Langage;
import scaffolding.TemplateSettings;

public class TemplateUtil {
    static private String modelTemp=System.getenv("templateDIR")+"/Model.temp";
    static private String controllerTemp=System.getenv("templateDIR")+"/Controller.temp";
    static String templateSettings=System.getenv("templateDIR")+"/templateConfig.json";
    public static String readModelTemp() throws FileNotFoundException, IOException{
        StringBuilder contenu = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(modelTemp))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                contenu.append(ligne).append("\n");
            }
        }
        return contenu.toString();
    }
    public static String readControllerTemp() throws FileNotFoundException, IOException{
        StringBuilder contenu = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(controllerTemp))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                contenu.append(ligne).append("\n");
            }
        }
        return contenu.toString();
    }
    public static String replaceSyntaxes(String toReplace, Langage langage){
        String regex = "@(.*?)@";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toReplace);
        String syntax = null;
        while(matcher.find()){
                        syntax = matcher.group();
            toReplace = toReplace.replace(syntax, langage.getSyntaxOf(StringUtil.removeFirstAndLastCharacter(syntax)));
        }
        return toReplace;
    }
    public static TemplateSettings readSettings() throws Exception{
        try (BufferedReader reader = new BufferedReader(new FileReader(templateSettings))) {
            Gson gson = new Gson();
            TemplateSettings settings = gson.fromJson(reader, TemplateSettings.class);
            return settings;
        } catch (IOException e) {
            throw e;
        }
    }
    
}
