package se.torsteneriksson.epidemicsimulation;

import java.util.Random;

/**
 * Utilities for common functions
 */
public class Utilities {
    private Random r = new Random();
    public boolean isHappening(int probability){
        if(r.nextDouble() <= probability/100.0)
            return true;
        else
            return false;
    }

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
