package se.torsteneriksson.epidemicsimulation.test;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.torsteneriksson.epidemicsimulation.UserInput;

import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {
    private UserInput ui;

    @BeforeEach
    void setUp() {
        String args[] = {"-cp", "0", "-dmi", "2", "-dma", "10", "-dp", "10", "-ps", "10"};
        ui = new UserInput(args);
    }

    @Test
    void getContaminationProbability() {
        Assert.assertTrue(ui.getContaminationProbability() == 0);
    }

    @Test
    void getSickDaysMin() {
        Assert.assertTrue(ui.getSickDaysMin() == 2);
    }

    @Test
    void getSickDaysMax() {
        Assert.assertTrue(ui.getSickDaysMax() == 10);
    }

    @Test
    void getDeathProbability() {
        Assert.assertTrue(ui.getDeathProbability() == 10);
    }

    @Test
    void getPopulationSize() {
        Assert.assertTrue(ui.getPopulationSize() == 10);
    }
}