namespace Model;

public class Book{
    
  private long idlivre;
  private string nomlivre;
  private DateTime datesortie;
  private DateTime datecreation;
    
    public long getIdlivre() {
        return idlivre;
    }
    public void setIdlivre(long idlivre) {
        this.idlivre = idlivre;
    }
    public string getNomlivre() {
        return nomlivre;
    }
    public void setNomlivre(string nomlivre) {
        this.nomlivre = nomlivre;
    }
    public DateTime getDatesortie() {
        return datesortie;
    }
    public void setDatesortie(DateTime datesortie) {
        this.datesortie = datesortie;
    }
    public DateTime getDatecreation() {
        return datecreation;
    }
    public void setDatecreation(DateTime datecreation) {
        this.datecreation = datecreation;
    }
}