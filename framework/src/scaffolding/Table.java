package scaffolding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Table {
    String nomTable;
    Column[] colonnes;
    public Table() {
    }
    public Table(String nomTable, Connection con,Langage langage) throws Exception{
        setNomTable(nomTable);
        if(con != null){
            setColonnes(getColonnes(langage,con));
        }
    }
    public Column[] getColonnes(Langage language, Connection connection) throws Exception{
        String sql = "SELECT * FROM "+nomTable+" limit 0";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        try { 
            ResultSet resultSet = pstmt.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
    
            colonnes = new Column[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                colonnes[i-1] = new Column(metaData.getColumnName(i),metaData.getColumnTypeName(i),language);
            }
            return colonnes;
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la lecture des colonnes");
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            pstmt.close();
            connection.close();
        }
        
    }
    public Table(String nomTable) {
        this.nomTable = nomTable;
    }
    public String getNomTable() {
        return nomTable;
    }
    public void setNomTable(String nomTable) {
        this.nomTable = nomTable;
    }
    public Column[] getColonnes() {
        return colonnes;
    }
    public void setColonnes(Column[] colonnes) {
        this.colonnes = colonnes;
    }
}