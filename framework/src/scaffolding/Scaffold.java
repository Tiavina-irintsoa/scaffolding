package scaffolding;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import util.DbConfigReader;

public class Scaffold {
    String motdepasse;
    Table table;
    Modele modele;
    String language;
    public static void generate(String motdepasse, String table, String nomModele,String nompackage,String language) throws Exception{
        Scaffold scaffold = new Scaffold(motdepasse, table, nomModele,nompackage,language);
        DbConfigReader reader = new DbConfigReader();
        DBConfig dbconfigs = reader.read();
        scaffold.getTable().getColonnes(motdepasse,dbconfigs,language);
        scaffold.createModelFile();
    }

    public Scaffold(String motdepasse, String table, String nomModele,String nompackage,String langage) {
        setLanguage(langage);
        setMotdepasse(motdepasse);
        setTable(table);
        if(nomModele.length()==0){
            nomModele = this.table.getNomTable();
        }
        this.setModele(new Modele(nomModele,nompackage));
        
    }
    public void createModelFile() throws Exception{
        if(language.compareTo("java")==0){
            createJavaModel();
        }
        else if(language.compareTo("csharp")==0){
            createCSharpModel();
        }
    }
    public void createCSharpModel() throws Exception{
        createFile("TemplateCSHARP.cs", "cs");
    }
    public void createFile(String templateName,String extension) throws Exception{
        String templateDIR = System.getenv("templateDIR");
        if(templateDIR == null){
            throw new Exception("Aucune variable d'environnement specifie pour templateDIR");
        }
        String templatePath = templateDIR+"/"+templateName;
        String contenuTemplate = Files.readString(Path.of(templatePath));

        contenuTemplate = contenuTemplate.replace("##classname##", modele.getFileName());
        contenuTemplate = contenuTemplate.replace("##import##", table.getImportCode(language));
        contenuTemplate = contenuTemplate.replace("##package##", modele.getNompackage());
        contenuTemplate = contenuTemplate.replace("##attributs##", table.getAttributsCode());
        contenuTemplate = contenuTemplate.replace("##getterssetters##", table.getGettersSettersCode(language));
        String newPath=modele.getFileName()+"."+extension;
        Files.writeString(Path.of(newPath), contenuTemplate, StandardOpenOption.CREATE);
        System.out.println("Scaffolding réussi. Le fichier a été créé avec succès : " + newPath);
    }
    public void createJavaModel() throws Exception {
        createFile("TemplateJAVA.java", "java");
    }
    public String getMotdepasse() {
        return motdepasse;
    }
    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
    public Table getTable() {
        return table;
    }
    public void setTable(String table) {
        this.table = new Table(table);
    }
    public void setTable(Table table) {
        this.table = table;
    }
    public Modele getModele() {
        return modele;
    }
    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
  
}
