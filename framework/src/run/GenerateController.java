package run;

import scaffolding.ScaffoldController;

public class GenerateController {
    public static void main(String[] args) {
        try {
            System.out.println("generation controller...");
            ScaffoldController.generate(args[0], args[1], args[2],args[3],args[4], args[5]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
