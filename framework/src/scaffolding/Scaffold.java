package scaffolding;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import util.DbConfigReader;
import util.LangageReader;

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

    public Langage getLangage(String langage) throws Exception{
        LangageReader langageReader = new LangageReader();
        Langage[] langages = langageReader.read();
        for (int i = 0; i < langages.length; i++) {
            if(langages[i].getNom().compareToIgnoreCase(langage) == 0){
                return langages[i];
            }
        }
        return null;
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
        Langage langage = getLangage(language);
        if(language.compareTo("java")==0){
            createJavaFiles("Entity.temp", "Controller.temp", "Repository.temp", "Service.temp", langage);
        }
        else if(language.compareTo("csharp")==0){
            createCsharpFiles("Entity.temp", "Controller.temp", langage);
        }
        
    }
    
    public void createCsharpFiles(String entityName, String controllerName, Langage langage) throws Exception{
        String templateDIR = System.getenv("templateDIR");
        if(templateDIR == null){
            throw new Exception("Aucune variable d'environnement specifie pour templateDIR");
        }
        templateDIR = templateDIR + "/csharp";

        String entityPath = templateDIR+"/"+entityName;
        String contenuTemplate = Files.readString(Path.of(entityPath));

        contenuTemplate = contenuTemplate.replace("##classname##", modele.getFileName());
        contenuTemplate = contenuTemplate.replace("##tablename##", table.getNomTable());
        contenuTemplate = contenuTemplate.replace("@import@", table.getImportCode(langage));
        contenuTemplate = contenuTemplate.replace("##package##", modele.getNompackage());
        contenuTemplate = contenuTemplate.replace("##attributs##", table.getAttributsCode(langage));
        contenuTemplate = contenuTemplate.replace("##getterssetters##", table.getGettersSettersCode(language));
        String newPath=modele.getFileName()+"." + langage.getProperties().getExt();
        Files.writeString(Path.of(newPath), contenuTemplate, StandardOpenOption.CREATE);
        System.out.println("Scaffolding réussi. Le fichier a été créé avec succès : " + newPath);

        String controllerPath = templateDIR+"/"+controllerName;
        String controllerTemplate = Files.readString(Path.of(controllerPath));

        controllerTemplate = controllerTemplate.replace("##classname##", modele.getFileName());
        controllerTemplate = controllerTemplate.replace("##package##", modele.getNompackage());
        String controlPath=modele.getFileName()+"Controller." + langage.getProperties().getExt();
        Files.writeString(Path.of(controlPath), controllerTemplate, StandardOpenOption.CREATE);
        System.out.println("Scaffolding réussi. Le fichier a été créé avec succès : " + controlPath);
    }

    public void createJavaFiles(String entityName, String controllerName, String repositoryName, String serviceName, Langage langage) throws Exception{
        String templateDIR = System.getenv("templateDIR") ;
        if(templateDIR == null){
            throw new Exception("Aucune variable d'environnement specifie pour templateDIR");
        }
        templateDIR = templateDIR + "/java";

        String entityPath = templateDIR+"/"+entityName;
        String contenuEntity = Files.readString(Path.of(entityPath));

        contenuEntity = contenuEntity.replace("##classname##", modele.getFileName());
        contenuEntity = contenuEntity.replace("##tablename##", table.getNomTable());
        contenuEntity = contenuEntity.replace("@import@", table.getImportCode(langage));
        contenuEntity = contenuEntity.replace("##package##", modele.getNompackage());
        contenuEntity = contenuEntity.replace("##attributs##", table.getAttributsCode(langage));
        contenuEntity = contenuEntity.replace("##getterssetters##", table.getGettersSettersCode(language));
        String entitPath=modele.getFileName()+"." + langage.getProperties().getExt();
        Files.writeString(Path.of(entitPath), contenuEntity, StandardOpenOption.CREATE);
        System.out.println("Scaffolding réussi. Le fichier a été créé avec succès : " + entitPath);

        String controllerPath = templateDIR+"/"+controllerName;
        String contenuTemplate = Files.readString(Path.of(controllerPath));

        contenuTemplate = contenuTemplate.replace("##package##", modele.getNompackage());
        contenuTemplate = contenuTemplate.replace("##classname##", modele.getFileName());
        String newPath=modele.getFileName()+"Controller." + langage.getProperties().getExt();
        Files.writeString(Path.of(newPath), contenuTemplate, StandardOpenOption.CREATE);

        String repositoryPath = templateDIR+"/"+repositoryName;
        String contenuRepository = Files.readString(Path.of(repositoryPath));

        contenuRepository = contenuRepository.replace("##package##", modele.getNompackage());
        contenuRepository = contenuRepository.replace("##classname##", modele.getFileName());
        String repPath=modele.getFileName()+"Repository." + langage.getProperties().getExt();
        Files.writeString(Path.of(repPath), contenuRepository, StandardOpenOption.CREATE);

        String servicePath = templateDIR+"/"+serviceName;
        String contenuService = Files.readString(Path.of(servicePath));

        contenuService = contenuService.replace("##classname##", modele.getFileName());
        String servPath=modele.getFileName()+"Service." + langage.getProperties().getExt();
        Files.writeString(Path.of(servPath), contenuService, StandardOpenOption.CREATE);

        System.out.println("Ces fichiers ont été créé avec succès : " + newPath + " " + repPath + " " + servPath);
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
