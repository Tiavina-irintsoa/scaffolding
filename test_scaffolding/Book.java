package modele;
import java.sql.Date;
import java.sql.Timestamp;

public class Book {
    
  private Long idlivre;
  private String nomlivre;
  private Date datesortie;
  private Timestamp datecreation;
    
    public Long getIdlivre() {
        return idlivre;
    }
    public void setIdlivre(Long idlivre) {
        this.idlivre = idlivre;
    }
    public String getNomlivre() {
        return nomlivre;
    }
    public void setNomlivre(String nomlivre) {
        this.nomlivre = nomlivre;
    }
    public Date getDatesortie() {
        return datesortie;
    }
    public void setDatesortie(Date datesortie) {
        this.datesortie = datesortie;
    }
    public Timestamp getDatecreation() {
        return datecreation;
    }
    public void setDatecreation(Timestamp datecreation) {
        this.datecreation = datecreation;
    }
}
