package scaffolding;

public class Column {
    String nomColonne;
    MyClasse classe;
    public Column(String nomColonne, String type,Langage language) throws Exception{
        setNomColonne(nomColonne);
        setClasse(language.getClassOf(type));
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
