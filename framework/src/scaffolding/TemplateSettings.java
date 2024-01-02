package scaffolding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class TemplateSettings {
    static String filePath="templateConfig.json";
    @SerializedName("package")
    TemplateTag packageTags;
    @SerializedName("import")
    TemplateTag importTags;
    @SerializedName("fields")
    TemplateTag fieldsTags;
    @SerializedName("methods")
    TemplateTag methodsTags;

    public TemplateSettings(){

    }
    public static TemplateSettings read() throws Exception{
        String templateDIR = System.getenv("templateDIR");
        if(templateDIR == null){
            throw new Exception("Aucune variable d'environnement specifie pour templateDIR");
        }
        String path = templateDIR + "/" + filePath;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Gson gson = new Gson();
            TemplateSettings settings = gson.fromJson(reader, TemplateSettings.class);
            return settings;
        } catch (IOException e) {
            throw e;
        }
    }
    public TemplateTag getPackageTags() {
        return packageTags;
    }
    public void setPackageTags(TemplateTag packageTags) {
        this.packageTags = packageTags;
    }
    public TemplateTag getImportTags() {
        return importTags;
    }
    public void setImportTags(TemplateTag importTags) {
        this.importTags = importTags;
    }
    public TemplateTag getFieldsTags() {
        return fieldsTags;
    }
    public void setFieldsTags(TemplateTag fieldsTags) {
        this.fieldsTags = fieldsTags;
    }
    public TemplateTag getMethodsTags() {
        return methodsTags;
    }
    public void setMethodsTags(TemplateTag methodsTags) {
        this.methodsTags = methodsTags;
    }
}
