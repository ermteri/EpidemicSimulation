package se.torsteneriksson.epidemicsimulation;

import java.util.Random;

/**
 * Utilities for common functions
 */
public class Utilities {
    final private Random r = new Random();

    /**
     * Check if the event should happen in this time.
     * @param probability probability in percent.
     * @return true if it happens.
     */
    public boolean isHappening(int probability){
        return r.nextDouble() <= probability / 100.0;
    }

    /**
     * Prints the result on console.
     * @param numberOfInfectedAccumulated
     * @param numberOfDeadAccumulated
     * @param numberOfIll
     * @param numberOfGetInfected
     * @param numberOfDied
     * @param numberOfRecovered
     */
    public void reportResult(UserInput ui, int numberOfInfectedAccumulated,
                             int numberOfDeadAccumulated,
                             int numberOfIll,
                             int numberOfGetInfected,
                             int numberOfDied,
                             int numberOfRecovered) {
        if(ui.getIfLogging()) {
            System.out.println("InfectedAccu:" + numberOfInfectedAccumulated +
                    ", Dead accu:" + numberOfDeadAccumulated +
                    ", Ill:" + numberOfIll +
                    ", Infected:" + numberOfGetInfected +
                    ", Died:" + numberOfDied +
                    ", Recovered:" + numberOfRecovered);
        }
    }

    public void reportDay(Population population) {
        int numHealthy = 0;
        int numSick = 0;
        int numImmune = 0;
        int numDead = 0;
        for(Object o: population) {
            HumanBeing hb = (HumanBeing)o;
            switch (hb.getHealthState()) {
                case HEALTHY: numHealthy++;
                break;
                case SICK: numSick++;
                break;
                case DEAD: numDead++;
                break;
            }
            if(hb.isImmune())
                numImmune++;
         }
        //System.out.printf("%d,%d,%d,%d\n",numHealthy,numSick,numImmune,numDead);
    }
    public void printUserInput(UserInput userInput) {
        if(userInput.getIfLogging()) {
            System.out.println("Simulation started!");
            System.out.println("Population size: " + userInput.getPopulationSize() * userInput.getPopulationSize());
            System.out.println("Contamination probability: " + userInput.getContaminationProbability() + "%");
            System.out.println("Sick days: " + userInput.getSickDaysMin() + " - " + userInput.getSickDaysMax() + " days");
            System.out.println("Death probability: " + userInput.getDeathProbability() + "%");
        }
    }
}
