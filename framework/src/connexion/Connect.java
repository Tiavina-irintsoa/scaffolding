package connexion;

import java.sql.*;

import scaffolding.DBConfig;
import util.DbConfigReader;

public class Connect {
  DBConfig dbconfigs;
  String password;

  public Connect(String password,DBConfig dbconfigs) throws Exception{
   
    setDbconfigs(dbconfigs);
    setPassword(password);
  }
  public String getDriver() throws Exception{
    if(dbconfigs.getServer().compareTo("postgresql")==0){
      return "org.postgresql.Driver";
    }
    else if (dbconfigs.getServer().compareTo("mysql")==0){  
      return "com.mysql.jdbc.Driver";
    }
    throw new Exception("Pilote JDBC introuvable");
  }
  
  public Connection getConnectionPostgresql() throws Exception {
  
    Connection connect = null;
    try {
      Class.forName(getDriver());
      connect =
        DriverManager.getConnection(
          dbconfigs.getConnectionString(),
          dbconfigs.getUsername(),
          getPassword()
        );
        connect.setAutoCommit(false);
    } catch (Exception e) {
      throw e;
    }
    return connect;
  }
  public DBConfig getDbconfigs() {
    return dbconfigs;
  }
  public void setDbconfigs(DBConfig dbconfigs) {
    this.dbconfigs = dbconfigs;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}
