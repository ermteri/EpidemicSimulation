package se.torsteneriksson.epidemicsimulation;

/**
 * Main class
 */
public class EpidemicSimulation {
    HumanBeing hb[];

    public static void main(String[] args) {

       Control controller = new Control(new UserInput(args));
       controller.init();
       controller.startSimulation();
    }
}
