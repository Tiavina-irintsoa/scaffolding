package scaffolding;

public class Column {
    String nomColonne;
    MyClasse classe;
    public Column(String nomColonne, String type,String language) throws Exception{
        this.nomColonne = nomColonne;
        setClasse(DynamicClassGenerator.getClass(type,language));
    }
    public String getNomColonne() {
        return nomColonne;
    }
    public void setNomColonne(String nomColonne) {
        this.nomColonne = nomColonne;
    }
    public MyClasse getClasse() {
        return classe;
    }
    public void setClasse(MyClasse classe) {
        this.classe = classe;
    }
    
}
