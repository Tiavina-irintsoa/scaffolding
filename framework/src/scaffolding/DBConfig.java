package scaffolding;
import com.google.gson.annotations.SerializedName;

public class DBConfig {
    @SerializedName("SGBD")
    String server;
    @SerializedName("nom_utilisateur")
    String username;
    @SerializedName("hote")
    String host;
    @SerializedName("base_de_donnees")
    String databaseName;
    @SerializedName("port")
    String port;

    public String getConnectionString() throws Exception{
        if(this.getServer().compareTo("postgresql")==0 || this.getServer().compareTo("mysql")==0){
          String conString =  "jdbc:"+this.getServer()+"://"+this.getHost()+":"+this.getPort()+"/"+this.getDatabaseName();
          System.out.println(conString);
          return conString;
        }
        throw new Exception("SGBD non specifie");
      } 
    public DBConfig(String server, String username, String host,String port) {
        this.server = server;
        this.username = username;
        this.host = host;
        this.port =port;
    }
    public DBConfig() {
    }
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
}
