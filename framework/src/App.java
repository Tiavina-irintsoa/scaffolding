import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private static String lireContenuFichier(String cheminFichier) throws IOException {
        StringBuilder contenu = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                contenu.append(ligne).append("\n");
            }
        }
        return contenu.toString();
    }
    public static void main(String[] args) throws IOException {
        String cheminFichier = System.getenv("templateDIR")+"/Template.temp";

        // Lecture du contenu du fichier
        String contenuFichier = lireContenuFichier(cheminFichier);

        // Modèle de l'expression régulière pour extraire le contenu entre les balises
        String regex = "\\$packagestart\\$(.*?)\\$packageend\\$";

        // Création du motif de l'expression régulière
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);

        // Création du correspondant (matcher) à partir du motif et du contenu du fichier
        Matcher matcher = pattern.matcher(contenuFichier);

        // Vérifie si une correspondance est trouvée
        if (matcher.find()) {
            // Récupération du contenu entre les balises
            String contenuEntreBalises = matcher.group(1);

            // Affichage du résultat
            System.out.println("Contenu entre les balises :");
            System.out.println(contenuEntreBalises);
        } else {
            System.out.println("Aucune correspondance trouvée.");
        }
    }       
}