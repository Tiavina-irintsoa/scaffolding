package run;

import scaffolding.ScaffoldModel;

public class GenerateModel {
    public static void main(String[] args) {
        try {
            System.out.println("generation modele...");
            ScaffoldModel.generate(args[0], args[1], args[2],args[3],args[4]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
