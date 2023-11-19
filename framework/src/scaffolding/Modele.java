package scaffolding;

import util.StringUtil;

public class Modele {
    String nomModele;
    String nompackage;

    public Modele(String nomModele, String nompackage) {
        this.nomModele = nomModele;
        this.nompackage = nompackage;
    }
    
    public String getFileName(){
        return StringUtil.capitalize(nomModele);
    }
    public Modele(String nomModele) {
        this.nomModele = nomModele;
    }

    public Modele() {
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public String getNompackage() {
        return nompackage;
    }

    public void setNompackage(String nompackage) {
        this.nompackage = nompackage;
    }
}
