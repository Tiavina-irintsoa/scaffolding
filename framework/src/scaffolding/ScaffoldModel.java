package scaffolding;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connexion.Connect;
import util.DbConfigReader;
import util.FileUtil;
import util.StringUtil;
import util.TemplateUtil;

public class ScaffoldModel {
    String motdepasse;
    Table table;
    MyClasse modele;
    Langage language;
    public static void generate(String motdepasse, String table, String nomModele,String nompackage,String language) throws Exception{
        TemplateSettings settings = TemplateUtil.readSettings();
        String templateContent = TemplateUtil.readModelTemp();
        DbConfigReader reader = new DbConfigReader();
        DBConfig dbconfigs = reader.read();
        Connect connect =  new Connect(motdepasse, dbconfigs);

        Connection con = connect.getConnectionPostgresql();
        ScaffoldModel scaffold = new ScaffoldModel(motdepasse, table, nomModele,nompackage,language,con);
        scaffold.createModelFile(settings,templateContent);
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
    public String getFileName(){
        return StringUtil.capitalize(modele.getClassName())+"."+language.getExtension();
    }
    public void createModelFile(TemplateSettings settings, String templateContent) throws Exception{
        templateContent = TemplateUtil.replaceSyntaxes(templateContent,getLanguage());
        String content = getDynamicContent(settings, templateContent);
        FileUtil.createAndWriteModel(getFileName(), content);
    }
    public String getDynamicContent(TemplateSettings settings, String templateContent){
        return getPackageCode(settings, templateContent) + getImportCode(settings, templateContent) + getClassDeclarationCode(settings, templateContent)+getFieldsDeclarationCode(settings, templateContent)+getMethodsDeclarationCode(settings, templateContent)+getEndClassCode(settings, templateContent);
    }
    public String getEndClassCode(TemplateSettings settings, String templateContent){
        String regex = settings.getEndClassRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        if(matcher.find()){
            betweenTags = matcher.group(1);
        }
        return betweenTags;
    }
    public String getMethodsDeclarationCode(TemplateSettings settings, String templateContent){
        String regex = settings.getMethodsRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        String code="";
        if(matcher.find()){
            betweenTags = matcher.group(1);
            for (int i = 0; i < getTable().getColonnes().length; i++) {
                code+=betweenTags;
                code=code.replace("##fieldtype##", getTable().getColonnes()[i].getClasse().getClassName());
                code=code.replace("##fieldname##",getTable().getColonnes()[i].getNomColonne());
                code=code.replace("##fieldupperfirst##",StringUtil.capitalize(getTable().getColonnes()[i].getNomColonne()));
            }
        }
        return code;   
    }
    public String getFieldsDeclarationCode(TemplateSettings settings, String templateContent){
        String regex = settings.getFieldsRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        String code="";
        if(matcher.find()){
            betweenTags = matcher.group(1);
            for (int i = 0; i < getTable().getColonnes().length; i++) {
                code+=betweenTags;
                code=code.replace("##fieldtype##", getTable().getColonnes()[i].getClasse().getClassName());
                code=code.replace("##fieldname##",getTable().getColonnes()[i].getNomColonne());
            }
        }
        return code;
    }
    public String getClassDeclarationCode(TemplateSettings settings, String templateContent){
        String regex = settings.getDeclarationRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        if(matcher.find()){
            betweenTags = matcher.group(1);
            betweenTags = betweenTags.replace("##classname##", StringUtil.capitalize(modele.getClassName()));
        }
        return betweenTags;
    }
    public String getImportCode(TemplateSettings settings, String templateContent){
        String regex = settings.getImportRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = null;
        String code = "";
        if(matcher.find()){
            betweenTags = matcher.group(1);
            Column[] distinct = table.getDistinctColumns();
            for (int index = 0; index < distinct.length; index++) {
                if(language.canImport(distinct[index].getClasse().getPackageName())){
                    code+=betweenTags;
                    code=code.replace("##import_value##", distinct[index].getClasse().toString());
                }
            }
        }
        return code;
    }


    public String getPackageCode(TemplateSettings settings, String templateContent){
        String regex = settings.getPackageRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        if(matcher.find()){
            betweenTags = matcher.group(1);
            betweenTags = betweenTags.replace("##package## ", modele.getPackageName());
        }
        return betweenTags;
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
