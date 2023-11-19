package scaffolding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import connexion.Connect;
import util.StringUtil;

public class Table {
    String nomTable;
    Column[] colonnes;

    public String getAttributsCode(){
        StringBuilder attributsCode = new StringBuilder();
        for (Column column : colonnes) {
            attributsCode.append("\n");
            attributsCode.append("  private "+column.getClasse().getClassName()+" "+column.getNomColonne()).append(";");
        }
        return attributsCode.toString();
    }
    
    public String getImportCode(String language){
        StringBuilder code = new StringBuilder();
        if(language.compareTo("java")==0){
            for (Column column : colonnes) {
                // Si le package est différent de java.lang, générer l'import
                if (!column.getClasse().getPackageName().equals("java.lang")) {
                    code.append("import ").append(column.getClasse().getPackageName()).append("."+column.getClasse().getClassName()+";\n");
                }
            }
        }
        else if(language.compareTo("csharp")==0){
            for (Column column : colonnes) {
                // Si le namespace est différent de System, générer le using statement
                if (!column.getClasse().getPackageName().equals("System")) {
                    code.append("using ").append(column.getClasse().getPackageName()).append(";\n");
                }   
            }
        }
        return code.toString();
    }
    public String getGettersSettersCode(String language){
        StringBuilder gettersSettersCode = new StringBuilder();
        if(language.compareTo("java")==0){
            for (Column column : colonnes) {
                gettersSettersCode.append("\n");
                gettersSettersCode.append("    public ").append(column.getClasse().getClassName()).append(" get").append(StringUtil.capitalize(column.getNomColonne())).append("() {");
                gettersSettersCode.append("\n");
                gettersSettersCode.append("        return ").append(column.getNomColonne()).append(";");
                gettersSettersCode.append("\n");
                gettersSettersCode.append("    }");

                // Ajouter le code du setter
                gettersSettersCode.append("\n");
                gettersSettersCode.append("    public void set").append(StringUtil.capitalize(column.getNomColonne())).append("(").append(column.getClasse().getClassName()).append(" ").append(column.getNomColonne()).append(") {");
                gettersSettersCode.append("\n");
                gettersSettersCode.append("        this.").append(column.getNomColonne()).append(" = ").append(column.getNomColonne()).append(";");
                gettersSettersCode.append("\n");
                gettersSettersCode.append("    }");
            }
        }
        else if(language.compareTo("csharp")==0){
            for (Column column : colonnes) {
                // Ajouter le code du getter
                gettersSettersCode.append("\n");
                gettersSettersCode.append("    public ").append(column.getClasse().getClassName()).append(" get").append(StringUtil.capitalize(column.getNomColonne())).append("() {");
                gettersSettersCode.append("\n");
                gettersSettersCode.append("        return ").append(column.getNomColonne()).append(";");
                gettersSettersCode.append("\n");
                gettersSettersCode.append("    }");

                // Ajouter le code du setter
                gettersSettersCode.append("\n");
                gettersSettersCode.append("    public void set").append(StringUtil.capitalize(column.getNomColonne())).append("(").append(column.getClasse().getClassName()).append(" ").append(column.getNomColonne()).append(") {");
                gettersSettersCode.append("\n");
                gettersSettersCode.append("        this.").append(column.getNomColonne()).append(" = ").append(column.getNomColonne()).append(";");
                gettersSettersCode.append("\n");
                gettersSettersCode.append("    }");
            }
        }
        return gettersSettersCode.toString();
    }
    public void getColonnes(String password,DBConfig configs,String language) throws Exception{
        Connect connect = new Connect(password,configs);
        Connection connection = connect.getConnectionPostgresql();
        String sql = "SELECT * FROM "+nomTable;
        System.out.println(sql);
        PreparedStatement pstmt = connection.prepareStatement(sql);
        try { 
            ResultSet resultSet = pstmt.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
    
            colonnes = new Column[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                colonnes[i-1] = new Column(metaData.getColumnName(i),metaData.getColumnTypeName(i),language);
                System.out.println(colonnes[i-1].getNomColonne()+":"+colonnes[i-1].getClasse().getClassName());
            }
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