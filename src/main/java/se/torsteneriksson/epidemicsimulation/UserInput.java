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
        } catch(NullPointerException e) {
            System.err.println("cp param not found!" + e.toString());
            return -1;
        } catch(NumberFormatException e) {
            System.err.println("cp param value not a number"+e.toString());
            return -2;
        }
    }

    public int getSickDaysMin() {
        return Integer.valueOf(mParams.get("dmi").get(0));
    }

    public int getSickDaysMax() {
        return Integer.valueOf(mParams.get("dma").get(0));
    }

    public int getDeathProbability() {
        return Integer.valueOf(mParams.get("dp").get(0));
    }

    public int getPopulationSize() {
        return Integer.valueOf(mParams.get("ps").get(0));
    }
}
