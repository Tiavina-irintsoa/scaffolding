package scaffolding;

import java.sql.Connection;

import connexion.Connect;
import util.DbConfigReader;

public class ScaffoldModel {
    String motdepasse;
    Table table;
    MyClasse modele;
    Langage language;
    public static void generate(String motdepasse, String table, String nomModele,String nompackage,String language) throws Exception{
        TemplateSettings settings = TemplateSettings.read();

        DbConfigReader reader = new DbConfigReader();
        DBConfig dbconfigs = reader.read();
        Connect connect =  new Connect(motdepasse, dbconfigs);
        Connection con = connect.getConnectionPostgresql();
        ScaffoldModel scaffold = new ScaffoldModel(motdepasse, table, nomModele,nompackage,language,con);
        scaffold.createModelFile(settings);
    }

    
    public ScaffoldModel(String motdepasse, String table, String nomModele,String nompackage,String langage,Connection connection) throws Exception {
        this.setModele(new MyClasse(nomModele,nompackage));
        setLanguage(Langage.getLangage(langage));
        setMotdepasse(motdepasse);
        
        setTable(table,connection);
        
        if(nomModele.length()==0){
            nomModele = this.table.getNomTable();
        }
    }
    public void createModelFile(TemplateSettings settings) throws Exception{
        
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


    public void setTable(Table table) {
        this.table = table;
    }


    public MyClasse getModele() {
        return modele;
    }


    public void setModele(MyClasse modele) {
        this.modele = modele;
    }


    public Langage getLanguage() {
        return language;
    }


    public void setLanguage(Langage language) {
        this.language = language;
    }
    
    public void setTable(String table, Connection con) throws Exception{
        setTable(new Table(table,con,language));
    }
}
