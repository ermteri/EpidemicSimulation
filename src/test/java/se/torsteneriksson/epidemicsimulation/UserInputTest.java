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

        ui = new UserInput(args);
        Assert.assertTrue(ui.getContaminationProbability() == ui.CONTAMINATIONPROPABILITY);
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
    void getSickDaysMinMissing() {
        String args[] = {"-cp", "2", "-dmX", "2", "-dma", "10", "-dp", "10", "-ps", "10"};

        ui = new UserInput(args);
        Assert.assertTrue(ui.getSickDaysMin() == ui.SICKDAYSMIN);
    }

    @Test
    void getSickDaysMinInvalid() {
        String args[] = {"-cp", "2", "-dmi", "w", "-dma", "10", "-dp", "10", "-ps", "10"};
        try {
            ui = new UserInput(args);
            ui.getSickDaysMin();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NumberFormatException);
            return;
        }
        Assert.fail("No exception thrown, expected NumberFormatException");
    }

    @Test
    void getSickDaysMax() {
        Assert.assertTrue(ui.getSickDaysMax() == 10);
    }

    @Test
    void getSickDaysMaxMissing() {
        String args[] = {"-cp", "2", "-dmi", "2", "-dmX", "10", "-dp", "10", "-ps", "10"};

        ui = new UserInput(args);
        Assert.assertTrue(ui.getSickDaysMax() == ui.SICKDAYSMAX);
    }

    @Test
    void getSickDaysMaxInvalid() {
        String args[] = {"-cp", "2", "-dmi", "2", "-dma", "s", "-dp", "10", "-ps", "10"};
        try {
            ui = new UserInput(args);
            ui.getSickDaysMax();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NumberFormatException);
            return;
        }
        Assert.fail("No exception thrown, expected NumberFormatException");
    }

    @Test
    void getDeathProbability() {
        Assert.assertTrue(ui.getDeathProbability() == 10);
    }

    @Test
    void getDeathProbabilityMissing() {
        String args[] = {"-cp", "2", "-dmi", "2", "-dmX", "10", "-dX", "10", "-ps", "10"};

        ui = new UserInput(args);
        Assert.assertTrue(ui.getDeathProbability() == ui.DEATHPROABILITY);
    }

    @Test
    void getDeathProbabilityInvalid() {
        String args[] = {"-cp", "2", "-dmi", "2", "-dmX", "10", "-dp", "1s", "-ps", "10"};
        try {
            ui = new UserInput(args);
            ui.getDeathProbability();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NumberFormatException);
            return;
        }
        Assert.fail("No exception thrown, expected NumberFormatException");
    }

    @Test
    void getPopulationSize() {
        Assert.assertTrue(ui.getPopulationSize() == 10);
    }

    @Test
    void getPopulationSizeMissing() {
        String args[] = {"-cp", "2", "-dmi", "2", "-dma", "10", "-dp", "10", "-pSs", "10"};

        ui = new UserInput(args);
        Assert.assertTrue(ui.getPopulationSize() == ui.POPULATIONSIZE);
    }

    @Test
    void getPopulationSizeInvalid() {
        String args[] = {"-cp", "2", "-dmi", "2", "-dma", "10", "-dp", "10", "-ps", "e10"};
        try {
            ui = new UserInput(args);
            ui.getPopulationSize();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NumberFormatException);
            return;
        }
        Assert.fail("No exception thrown, expected NumberFormatException");
    }
}