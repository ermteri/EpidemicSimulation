package se.torsteneriksson.epidemicsimulation;

import org.junit.Assert;

class HumanBeingTest {

    @org.junit.jupiter.api.Test
    void setGetHealthState() {
        HumanBeing hb = new HumanBeing();
        Assert.assertTrue(hb.getHealthState() == HumanBeing.Healthstate.HEALTHY);
        hb.setHealthState(HumanBeing.Healthstate.SICK);
        Assert.assertTrue(hb.getHealthState() == HumanBeing.Healthstate.SICK);
        hb.setHealthState(HumanBeing.Healthstate.DEAD);
        Assert.assertTrue(hb.getHealthState() == HumanBeing.Healthstate.DEAD);
    }

    @org.junit.jupiter.api.Test
    void incremecntSickDays() {
        HumanBeing hb = new HumanBeing();
        Assert.assertTrue(hb.getSickDays() == 0);
        hb.incremecntSickDays();
        Assert.assertTrue(hb.getSickDays() == 1);
        hb.incremecntSickDays();
        hb.incremecntSickDays();
        hb.incremecntSickDays();
        Assert.assertTrue(hb.getSickDays() == 4);
    }

    @org.junit.jupiter.api.Test
    void getSickDays() {
        HumanBeing hb = new HumanBeing();
        Assert.assertTrue(hb.getSickDays() == 0);
        hb.incremecntSickDays();
        hb.incremecntSickDays();
        Assert.assertTrue(hb.getSickDays() == 2);
    }

    @org.junit.jupiter.api.Test
    void setIsImune() {
        HumanBeing hb = new HumanBeing();
        Assert.assertFalse(hb.isImune());
        hb.setImune(true);
        Assert.assertTrue(hb.isImune());
        hb.setImune(false);
        Assert.assertFalse(hb.isImune());
    }

    @org.junit.jupiter.api.Test
    void checkIfRecovered() {
        HumanBeing hb = new HumanBeing();
        hb.incremecntSickDays();
        hb.incremecntSickDays();
        hb.incremecntSickDays();
        //hb.incremecntSickDays();*/
        int recovered = 0;
        int notRecovered = 0;
        for(int i = 0;i < 10000;i++) {
            if(hb.checkIfRecovered(2,10))
                recovered++;
            else
                notRecovered++;
        }
        System.out.println("Recovered:" + recovered + " Not recovered:" + notRecovered);
        Assert.assertTrue(recovered >2100 && recovered < 2400);
    }

    @org.junit.jupiter.api.Test
    void checkIfDied() {
        Utilities util = new Utilities();
        HumanBeing hb = new HumanBeing();
        Assert.assertTrue(hb.getHealthState() == HumanBeing.Healthstate.HEALTHY);
        hb.incremecntSickDays();
        hb.setHealthState(HumanBeing.Healthstate.SICK);
        int dead = 0;
        for(int i = 0;i < 10000 ;i++) {
            if(hb.checkIfDied(util, 30))
                dead++;
            hb.setHealthState(HumanBeing.Healthstate.SICK);
        }
        System.out.println("Dead:" +  dead);
        Assert.assertTrue(dead >2900 && dead <3100);
    }
}