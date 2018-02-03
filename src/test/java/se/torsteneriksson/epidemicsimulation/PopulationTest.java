package se.torsteneriksson.epidemicsimulation;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import se.torsteneriksson.epidemicsimulation.HumanBeing;
import se.torsteneriksson.epidemicsimulation.Population;

class PopulationTest {

    @Test
    void iterator() {
        HumanBeing hb[][] = new HumanBeing[10][10];
        Population population = new Population(hb);
        int numEntries = 0;
        for(Object o: population) {
            numEntries++;
        }
        Assert.assertEquals(100,numEntries);

        numEntries = 0;
        for(Object o: population) {
            numEntries++;
        }
        Assert.assertEquals(100,numEntries);
    }

    @Test
    void getSize() {
        HumanBeing hb[][] = new HumanBeing[10][10];
        Population population = new Population(hb);
        Assert.assertTrue(population.getSize() == 100);
    }


    @Test
    void checkIfBeInfected() {
        HumanBeing hb[][] = new HumanBeing[3][3];
        Population population = new Population(hb);
        for(Object o: population) {
            HumanBeing humanBeing = (HumanBeing)o;
            int numOfSick = 0;
            if(humanBeing.getHealthState() != HumanBeing.Healthstate.SICK) {
                for(int i = 0;i < 10000;i++) {
                //System.out.println("HB:" + humanBeing.getHealthState());
                    if (population.checkIfBeInfected(30)) {
                        numOfSick++;
                    }
                    // Reset the healthState since we are looping over the same human being.
                    humanBeing.setHealthState(HumanBeing.Healthstate.HEALTHY);
                }
                System.out.println("numOfSick: " + numOfSick);
                Assert.assertTrue(numOfSick > 2700 && numOfSick < 3300);
            }
        }
    }
}