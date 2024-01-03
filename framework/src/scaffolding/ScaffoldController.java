package scaffolding;

import java.io.IOException;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connexion.Connect;
import util.DbConfigReader;
import util.FileUtil;
import util.StringUtil;
import util.TemplateUtil;

public class ScaffoldController extends Scaffold {
    String url;
    Column primaryKey;

    public static void generate(String motdepasse, String packagename, String classe, String url, String colonnepk,
            String langage) throws Exception {
        TemplateSettings settings = TemplateUtil.readSettings();
        String templateContent = TemplateUtil.readControllerTemp();
        DbConfigReader reader = new DbConfigReader();
        DBConfig dbconfigs = reader.read();
        Connect connect = new Connect(motdepasse, dbconfigs);
        Connection con = connect.getConnectionPostgresql();
        ScaffoldController scaffold = new ScaffoldController(motdepasse, packagename, classe, url, colonnepk, langage);
        scaffold.createFile(settings, templateContent);
    }

    public ScaffoldController() {
        super();
    }

    @Override
    public void createFile(TemplateSettings settings, String templateContent) throws IOException {
        templateContent = TemplateUtil.replaceSyntaxes(templateContent, getLangage());
        String content = getDynamicContent(settings, templateContent);
        FileUtil.createAndWriteController(getFileName(), content);
    }

    public String getDynamicContent(TemplateSettings settings, String templateContent) {
        return getPackageCode(settings, templateContent) + getImportCode(settings, templateContent)
                + getClassDeclarationCode(settings, templateContent)
                + getMethodsDeclarationCode(settings, templateContent) + getEndClassCode(settings, templateContent);
    }

    public String getEndClassCode(TemplateSettings settings, String templateContent) {
        String regex = settings.getEndClassRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        if (matcher.find()) {
            betweenTags = matcher.group(1);
        }
        return betweenTags;
    }

    public String getMethodsDeclarationCode(TemplateSettings settings, String templateContent) {
        String regex = settings.getMethodsRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        if (matcher.find()) {
            betweenTags = matcher.group(1);
            betweenTags = betweenTags.replace("##pkfield##", primaryKey.getNomColonne());
            betweenTags = betweenTags.replaceAll("##url##", getUrl());
        }
        return betweenTags;
    }

    public String getClassDeclarationCode(TemplateSettings settings, String templateContent) {
        String regex = settings.getDeclarationRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        if (matcher.find()) {
            betweenTags = matcher.group(1);
            betweenTags = betweenTags.replace("##classname##", StringUtil.capitalize(modele.getClassName()));
        }
        return betweenTags;
    }

    public String getImportCode(TemplateSettings settings, String templateContent) {
        String regex = settings.getImportRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        String code = "";
        if (matcher.find()) {
            betweenTags = matcher.group(1);
            for (int i = 0; i < langage.getImportPackage().length; i++) {
                code += betweenTags;
                code = code.replace("##import_value##", langage.getImportPackage()[i]);
            }
        }
        return code;
    }

    public String getPackageCode(TemplateSettings settings, String templateContent) {
        String regex = settings.getPackageRegex();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(templateContent);
        String betweenTags = "";
        if (matcher.find()) {
            betweenTags = matcher.group(1);
            betweenTags = betweenTags.replace("##package##", modele.getPackageName());
        }
        return betweenTags;
    }

    @Override
    public String getFileName() {
        return StringUtil.capitalize(modele.getClassName()) + "." + langage.getExtension();
    }

    public ScaffoldController(String motdepasse, String packageName, String classe, String url, String colonnepk,
            String langage) throws Exception {
        super();
        setPrimaryKey(new Column(colonnepk));
        setModele(new MyClasse(classe, packageName));
        setLangage(Langage.getLangage(langage));
        setUrl(url);
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
}
