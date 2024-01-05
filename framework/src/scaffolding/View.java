package scaffolding;

import java.util.HashMap;

import com.google.gson.annotations.SerializedName;

import util.ViewReader;

public class View {
    @SerializedName("langage")
    String nom;
    @SerializedName("ext")
    String extension;
    HashMap<String, String> syntaxes;
    
    public View() {
    }

    public static View getView(String View) throws Exception{
        ViewReader ViewReader = new ViewReader();
        View[] Views = ViewReader.read();
        for (int i = 0; i < Views.length; i++) {
            if(Views[i].getNom().compareToIgnoreCase(View) == 0){
                return Views[i];
            }
        }
        return null;
    }
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public HashMap<String, String> getSyntaxes() {
        return syntaxes;
    }
    public void setSyntaxes(HashMap<String, String> syntaxes) {
        this.syntaxes = syntaxes;
    }
}
