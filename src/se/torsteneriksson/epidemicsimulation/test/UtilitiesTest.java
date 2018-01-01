package se.torsteneriksson.epidemicsimulation.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import se.torsteneriksson.epidemicsimulation.Utilities;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void isHappening() {
        Utilities util = new Utilities();
        int happen = 0;
        for(int i = 0; i < 10000; i++) {
            if(util.isHappening(60))
                happen++;
        }
        System.out.println("Happen:" + happen);
        Assert.assertTrue(happen > 5900 && happen < 6100);

    }
}