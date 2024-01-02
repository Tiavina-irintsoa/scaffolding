package scaffolding;

import util.StringUtil;

public class Scaffold {
    String motdepasse;
    MyClasse modele;
    Langage langage;
    public Scaffold() {
    }
    public String getMotdepasse() {
        return motdepasse;
    }
    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
    public MyClasse getModele() {
        return modele;
    }
    public void setModele(MyClasse modele) {
        this.modele = modele;
    }
    public Langage getLangage() {
        return langage;
    }
    public String getClassDeclarationCode(TemplateSettings settings, String templateContent){
        return "";
    }
    public String getFileName(){
        return StringUtil.capitalize(modele.getClassName())+"."+langage.getExtension();
    }
    public void createFile(TemplateSettings settings, String templateContent) throws Exception{}

    public void setLangage(Langage langage) {
        this.langage = langage;
    }
}
