package se.torsteneriksson.epidemicsimulation;

import java.nio.IntBuffer;
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
        for (int i = 0; i < args.length; i++) {
            final String a = args[i];

            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return;
                }

                options = new ArrayList<>();
                mParams.put(a.substring(1), options);
            }
            else if (options != null) {
                options.add(a);
            }
            else {
                System.err.println("Illegal parameter usage");
                return;
            }
        }
    }
    public int getContaminationProbability() {
        return Integer.valueOf(mParams.get("cp").get(0));
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
