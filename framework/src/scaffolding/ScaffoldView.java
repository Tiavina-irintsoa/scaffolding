package scaffolding;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connexion.Connect;
import util.DbConfigReader;
import util.FileUtil;
import util.StringUtil;
import util.TemplateUtil;

public class ScaffoldView extends Scaffold {
    Table table;
    Column primaryKey;

    public static void generate(String motdepasse, String nompackage, String classe, String colonnepk, String langage, int option) throws Exception{
        TemplateSettings settings = TemplateUtil.readSettings();
        String templateContent = TemplateUtil.readFormTemp();
        if(option == 2){ //1 form //2 list
            templateContent = TemplateUtil.readListTemp();
        }
        DbConfigReader reader = new DbConfigReader();
        DBConfig dbconfigs = reader.read();
        Connect connect =  new Connect(motdepasse, dbconfigs);
        Connection con = connect.getConnectionPostgresql();
        ScaffoldView scaffold = new ScaffoldView(motdepasse, nompackage, classe, colonnepk, langage);
        scaffold.createFile(settings,templateContent);
    }

    public ScaffoldView(String motdepasse, String nompackage, String classe, String colonnepk, String langage) throws Exception {
        super();
        setPrimaryKey(new Column(colonnepk));
        setModele(new MyClasse(classe, nompackage));
        // setLangage(Langage.getLangage(langage));
        setView(View.getView(langage));
        setMotdepasse(motdepasse);
    }
   
    public void createFile(TemplateSettings settings, String templateContent) throws Exception{
        templateContent = TemplateUtil.replaceSyntaxes(templateContent, getLangage());
        String content = getDynamicContent(settings, templateContent);
        FileUtil.createAndWriteModel(getFileName(), content);
    }

    public String getDynamicContent(TemplateSettings settings, String templateContent) throws ClassNotFoundException{
        return getImportsCode(settings, templateContent) + getEnteteCode(settings, templateContent) + getObjectCode(settings, templateContent);
    }

    public String getEnteteCode(TemplateSettings settings, String templateContent) throws ClassNotFoundException{
        String regex = settings.getEnteteRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        String code = "";
        Class<?> classe = Class.forName( modele.getClassName() );
        if(matcher.find()){
            betweenTags = matcher.group(1);
            for (int i = 0; i < classe.getDeclaredFields().length; i++) {
                code+=betweenTags;
                code=code.replace("##fieldname##", StringUtil.capitalize(classe.getDeclaredFields()[i].getName()));
            }
        }
        return code;
    }

    public String getObjectCode(TemplateSettings settings, String templateContent) throws ClassNotFoundException{
        String regex = settings.getObjectRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        String code = "";
        Class<?> classe = Class.forName( modele.getClassName() );
        if(matcher.find()){
            betweenTags = matcher.group(1);
            for (int i = 0; i < classe.getDeclaredFields().length; i++) {
                code+=betweenTags;
                code = code.replace("##classname##", modele.getClassName().toLowerCase());
                code=code.replace("##fieldname##", StringUtil.capitalize(classe.getDeclaredFields()[i].getName()));
            }
        }
        return code;
    }

    public String getImportsCode(TemplateSettings settings, String templateContent){
        String regex = settings.getImportsRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        if(matcher.find()){
            betweenTags = matcher.group(1);
            if(getView().getNom() != "csharp" || getView().getNom() != "dotnet"){
                betweenTags = betweenTags.replace("##type##", StringUtil.capitalize(modele.getClassName()));
                betweenTags = betweenTags.replace("##list##", modele.getClassName().toLowerCase()+ "s");
            }
        }
        return betweenTags;
    }

    
    public Column getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Column primaryKey) {
        this.primaryKey = primaryKey;
    }
}
