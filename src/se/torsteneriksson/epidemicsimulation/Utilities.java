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
    public void reportResult(int numberOfInfectedAccumulated,
                             int numberOfDeadAccumulated,
                             int numberOfIll,
                             int numberOfGetInfected,
                             int numberOfDied,
                             int numberOfRecovered) {
        System.out.println("InfectedAccu:" + numberOfInfectedAccumulated +
                ", Dead accu:" + numberOfDeadAccumulated+
                ", Ill:" + numberOfIll +
                ", Infected:"+numberOfGetInfected +
                ", Died:" + numberOfDied +
                ", Recovered:"+ numberOfRecovered);
    }
}
