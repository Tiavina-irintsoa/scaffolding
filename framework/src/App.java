import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        String inputString = "@tab@@publicencaps@ @voidtype@";

        // Modèle de l'expression régulière pour extraire les mots entre @ et @
        String regex = "@(.*?)@";

        // Création du motif de l'expression régulière
        Pattern pattern = Pattern.compile(regex);

        // Création du correspondant (matcher) à partir du motif et de la chaîne d'entrée
        Matcher matcher = pattern.matcher(inputString);

        // Parcourir toutes les correspondances
        while (matcher.find()) {
            // Récupérer le mot entre @ et @ et afficher le résultat
            System.out.println("Mot trouvé : " + matcher.group());
        }
    }
}
