package scaffolding;
import com.google.gson.annotations.SerializedName;

public class Properties {
    @SerializedName("package")
    String paquet;
    @SerializedName("import")
    String imports;
    String ext;
    String[] notimport;
    
    public Properties() {
    }

    public Properties(String paquet, String imports, String ext, String[] notimport) {
        this.paquet = paquet;
        this.imports = imports;
        this.ext = ext;
        this.notimport = notimport;
    }

    public String getPaquet() {
        return paquet;
    }
    public void setPaquet(String paquet) {
        this.paquet = paquet;
    }
    public String getImports() {
        return imports;
    }
    public void setImports(String imports) {
        this.imports = imports;
    }
    public String getExt() {
        return ext;
    }
    public void setExt(String ext) {
        this.ext = ext;
    }
    public String[] getNotimport() {
        return notimport;
    }
    public void setNotimport(String[] notimport) {
        this.notimport = notimport;
    }
}
