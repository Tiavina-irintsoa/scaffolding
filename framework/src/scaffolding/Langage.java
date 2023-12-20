package scaffolding;
import com.google.gson.annotations.SerializedName;

public class Langage {
    @SerializedName("langage")
    String nom;
    Properties properties;

    public Langage() {
    }

    public Langage(String nom, Properties properties) {
        this.nom = nom;
        this.properties = properties;
    }
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Properties getProperties() {
        return properties;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
