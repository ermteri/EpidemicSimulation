package se.torsteneriksson.epidemicsimulation;

import java.util.Random;

import static se.torsteneriksson.epidemicsimulation.HumanBeing.Healthstate.HEALTHY;
import static se.torsteneriksson.epidemicsimulation.HumanBeing.Healthstate.SICK;

/**
 * Represents one person in the population
 */
public class HumanBeing {
    public enum Healthstate {HEALTHY, SICK, DEAD}
    private Healthstate healthState = HEALTHY;
    private int sickDays = 0;
    private boolean imune = false;

    public Healthstate getHealthState() {
        return healthState;
    }

    public void setHealthState(Healthstate healthState) {
        this.healthState = healthState;
    }

    public void incremecntSickDays() {
        this.sickDays++;
    }

    public int getSickDays() {
        return this.sickDays;
    }

    public boolean isImune() {
        return imune;
    }

    public void setImune(boolean imune) {
        this.imune = imune;
    }

    /**
     * Returns true if person recovered
     * @param min
     * @param max
     * @return
     */
    public boolean checkIfRecovered(int min, int max) {
        Random r = new Random();
        int result = min + (int) (r.nextDouble()*(max-min+1));

        //System.out.println("Result:"+ result);
        if(sickDays >= result) {
            healthState = HEALTHY;
            imune = true;
            return true;
        }
        return false;
    }

    /**
     * Returns true if person died
     * @param util
     * @param probability
     */
    public boolean checkIfDied(Utilities util, int probability) {
        if(healthState == SICK) {
            if (util.isHappening(probability)) {
                setHealthState(Healthstate.DEAD);
                return true;
            }
        }
        return false;
    }
}
