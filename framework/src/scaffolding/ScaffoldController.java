package scaffolding;

import java.sql.Connection;

import connexion.Connect;
import util.DbConfigReader;
import util.StringUtil;
import util.TemplateUtil;

public class ScaffoldController extends Scaffold{
    String url;
    Column primaryKey;
    MyClasse controller;
    public static void generate(String motdepasse, String packagename, String classe,String url, String colonnepk,String langage, String controllerpackage, String controllerClasse) throws Exception{
        TemplateSettings settings = TemplateUtil.readSettings();
        String templateContent = TemplateUtil.readControllerTemp();
        DbConfigReader reader = new DbConfigReader();
        DBConfig dbconfigs = reader.read();
        Connect connect =  new Connect(motdepasse, dbconfigs);
        Connection con = connect.getConnectionPostgresql();
        ScaffoldController scaffold = new ScaffoldController(motdepasse, packagename, classe, url, colonnepk, langage,controllerpackage,controllerClasse);
        scaffold.createFile(settings,templateContent);
    }
    public ScaffoldController(){
        super();
    }
    @Override
    public void createFile(TemplateSettings settings, String templateContent){
        templateContent = TemplateUtil.replaceSyntaxes(templateContent,getLangage());
        System.out.println(templateContent);
    }

    @Override
    public String getFileName(){
        return StringUtil.capitalize(controller.getClassName())+"."+langage.getExtension();
    }
    public ScaffoldController(String motdepasse,String packageName,String classe, String url,String colonnepk,String langage,String controllerPackage, String controllerClasse) throws Exception{
        super();
        setModele(new MyClasse(classe,packageName));
        setController(new MyClasse(controllerClasse, controllerPackage));
        setLangage(Langage.getLangage(langage));
        setMotdepasse(motdepasse);
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Column getPrimaryKey() {
        return primaryKey;
    }
    public void setPrimaryKey(Column primaryKey) {
        this.primaryKey = primaryKey;
    }
    public MyClasse getController() {
        return controller;
    }
    public void setController(MyClasse controller) {
        this.controller = controller;
    }
   
}
