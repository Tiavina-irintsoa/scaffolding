package scaffolding;
import java.util.HashMap;

import com.google.gson.annotations.SerializedName;

import util.LangageReader;

public class Langage {
    @SerializedName("langage")
    String nom;
    @SerializedName("ext")
    String extension;
    HashMap<String, String> syntaxes;
    @SerializedName("notimport")
    String[] notImport;
    @SerializedName("types")
    HashMap<String,MyClasse> typeCorresponding;

    public MyClasse getClassOf(String type){
        return typeCorresponding.get(type);
    }
    public String getSyntaxOf(String templateSyntax){
        return syntaxes.get(templateSyntax);
    }
    public static Langage getLangage(String langage) throws Exception{
        LangageReader langageReader = new LangageReader();
        Langage[] langages = langageReader.read();
        for (int i = 0; i < langages.length; i++) {
            if(langages[i].getNom().compareToIgnoreCase(langage) == 0){
                return langages[i];
            }
        }
        return null;
    }
    public boolean canImport(String packageName){
        for (int i = 0; i < notImport.length; i++) {
            if(packageName.compareTo(notImport[i])==0){
                return false;
            }
        }
        return true;
    }
    public Langage() {
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

    public String[] getNotImport() {
        return notImport;
    }

    public void setNotImport(String[] notImport) {
        this.notImport = notImport;
    }

    public HashMap<String, String> getSyntaxes() {
        return syntaxes;
    }

    public void setSyntaxes(HashMap<String, String> syntaxes) {
        this.syntaxes = syntaxes;
    }
    public HashMap<String, MyClasse> getTypeCorresponding() {
        return typeCorresponding;
    }
    public void setTypeCorresponding(HashMap<String, MyClasse> typeCorresponding) {
        this.typeCorresponding = typeCorresponding;
    }
    @Override
    public String toString() {
        return "Langage [nom=" + nom + ", extension=" + extension + ", syntaxes=" + syntaxes + ", notImport="
                + notImport + ", typeCorresponding=" + typeCorresponding + "]";
    }
}
