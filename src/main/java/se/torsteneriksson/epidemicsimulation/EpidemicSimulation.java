package se.torsteneriksson.epidemicsimulation;

/**
 * Main class
 */
public class EpidemicSimulation {

    public static void main(String[] args) {
        UserInput userInput = new UserInput((args));
        int numberOfSimulations = userInput.getNumberOfSimulations();
        Control controller = new Control(userInput);
        controller.init();
        int sum = 0;
        int i = 0;
        for(i = 0;i < numberOfSimulations;i++) {
            int percentSick = controller.startSimulation();
            controller.reset();
            if(userInput.getIfLogging())
                System.out.println(i+","+percentSick);
            sum = sum + percentSick;
        }
        System.out.println(userInput.getContaminationProbability() +","+sum/i);
    }
}
