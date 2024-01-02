package util;

public class StringUtil {
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    public static String regex(String start, String end){
        return "\\$"+start+"\\$(.*?)\\$"+end+"\\$";
    }
    public static String removeFirstAndLastCharacter(String str) {
        if (str != null && str.length() > 1) {
            // Enlever le premier caractère et le dernier caractère
            return str.substring(1, str.length() - 1);
        } else {
            // Retourner la chaîne inchangée si elle est vide ou a une longueur de 1
            return str;
        }
    }
}
