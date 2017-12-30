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
}
