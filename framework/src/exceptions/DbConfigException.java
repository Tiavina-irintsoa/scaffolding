package exceptions;

public class DbConfigException extends Exception{
    String message;
    public DbConfigException() {
        super("Fichier de configuration incomplet");
    }
}
