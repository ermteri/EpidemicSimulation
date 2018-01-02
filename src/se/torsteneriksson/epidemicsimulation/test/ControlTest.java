package se.torsteneriksson.epidemicsimulation.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.torsteneriksson.epidemicsimulation.Control;
import se.torsteneriksson.epidemicsimulation.UserInput;

class ControlTest {
    private Control ctl;
    @BeforeEach
    void setUp() {
        String args[] = {"-cp", "0", "-dmi", "2", "-dma", "10", "-dp", "10", "-ps", "10"};
        ctl = new Control(new UserInput(args));
    }

    @Test
    void init() {
        ctl.init();
    }

    @Test
    void startSimulation() {
        ctl.init();
        ctl.startSimulation();
    }
}