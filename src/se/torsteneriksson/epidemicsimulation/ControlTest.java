package se.torsteneriksson.epidemicsimulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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