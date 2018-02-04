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
    private boolean immune = false;

    public Healthstate getHealthState() {
        return healthState;
    }

    public void setHealthState(Healthstate healthState) {
        this.healthState = healthState;
    }

    public void incrementSickDays() {
        this.sickDays++;
    }

    public int getSickDays() {
        return this.sickDays;
    }
    public void setSickDays(int days) {
        sickDays = days;
    }

    public boolean isImmune() {
        return immune;
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
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
        if(healthState == SICK && sickDays >= result) {
            healthState = HEALTHY;
            immune = true;
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
