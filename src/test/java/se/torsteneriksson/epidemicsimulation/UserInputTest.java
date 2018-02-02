package se.torsteneriksson.epidemicsimulation;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void getContaminationProbabilityCpMissing() {
        String args[] = {"-cd", "0", "-dmi", "2", "-dma", "10", "-dp", "10", "-ps", "10"};
        try {
            ui = new UserInput(args);
            ui.getContaminationProbability();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NullPointerException);
            return;
        }
        Assert.fail("No exception thrown, expected NullPointerException");
    }

    @Test
    void getContaminationProbabilityCpInvalid() {
        String args[] = {"-cp", "w", "-dmi", "2", "-dma", "10", "-dp", "10", "-ps", "10"};
        try {
            ui = new UserInput(args);
            ui.getContaminationProbability();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NumberFormatException);
            return;
        }
        Assert.fail("No exception thrown, expected NumberFormatException");
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