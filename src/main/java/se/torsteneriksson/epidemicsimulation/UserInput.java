package se.torsteneriksson.epidemicsimulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains the settings as specified by the user
 */


public class UserInput {
    final private Map<String, List<String>> mParams = new HashMap<>();

    // Default values
    public int CONTAMINATIONPROPABILITY = 30;
    public int SICKDAYSMIN = 2;
    public int SICKDAYSMAX = 5;
    public int DEATHPROABILITY = 10;
    public int POPULATIONSIZE = 100;


    public UserInput(String[] args) {
        List<String> options = null;
        for (final String a : args) {
            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return;
                }

                options = new ArrayList<>();
                mParams.put(a.substring(1), options);
            } else if (options != null) {
                options.add(a);
            } else {
                System.err.println("Illegal parameter usage");
                return;
            }
        }
    }
    public int getContaminationProbability() {
        try {
            return Integer.valueOf(mParams.get("cp").get(0));
        } catch (NullPointerException e) {
            return CONTAMINATIONPROPABILITY;
        }
    }

    public int getSickDaysMin() {
        try {
            return Integer.valueOf(mParams.get("dmi").get(0));
        } catch (NullPointerException e) {
            return SICKDAYSMIN;
        }
    }

    public int getSickDaysMax() {
        try {
            return Integer.valueOf(mParams.get("dma").get(0));
        } catch (NullPointerException e) {
            return SICKDAYSMAX;
        }
    }

    public int getDeathProbability() {
        try {
            return Integer.valueOf(mParams.get("dp").get(0));
        } catch (NullPointerException e) {
            return DEATHPROABILITY;
        }
    }

    public int getPopulationSize() {

        try {
            return Integer.valueOf(mParams.get("ps").get(0));
        } catch (NullPointerException e) {
            return POPULATIONSIZE;
        }
    }
}
