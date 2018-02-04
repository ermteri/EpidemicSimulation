package se.torsteneriksson.epidemicsimulation;

/**
 * Responsible for execution of the simulation
 */
public class Control {
    private Population mPopulation;
    private int numberOfInfectedAccumulated;
    private int numberOfDeadAccumulated;
    final private UserInput mUserInput;
    final private Utilities mUtil = new Utilities();

    public Control(UserInput userInput){
        this.mUserInput = userInput;
    }

    public void init() {
        HumanBeing hb[][] = new HumanBeing[mUserInput.getPopulationSize()][mUserInput.getPopulationSize()];
        mPopulation = new Population(hb);
    }

    public void reset() {
        for(Object o: mPopulation) {
            HumanBeing hb = (HumanBeing)o;
            hb.setHealthState(HumanBeing.Healthstate.HEALTHY);
            hb.setImmune(false);
            hb.setSickDays(0);
        }
        mPopulation.setInitialState();
    }
    public int startSimulation() {
        mUtil.printUserInput(mUserInput);
        int numberOfDays = 0;
        numberOfInfectedAccumulated = 0;
        numberOfDeadAccumulated = 0;
        while(loopOneDay()) {
            numberOfDays++;
            mUtil.reportDay(mPopulation);
        }
        int percentSick = 100 * numberOfInfectedAccumulated / mPopulation.getSize();
        if(mUserInput.getIfLogging()) {
            System.out.println("Simulation done after " + numberOfDays + " days with " +
                    percentSick + "% infected");
        }
        return percentSick;
    }

    private boolean loopOneDay() {
        int numberOfIll = 0;
        int numberOfGetInfected = 0;
        int numberOfDied = 0;
        int numberOfRecovered = 0;

        for(Object o: mPopulation) {
            HumanBeing hb = (HumanBeing)o;
            if(mPopulation.checkIfBeInfected(mUserInput.getContaminationProbability())) {
                numberOfGetInfected++;
            }
            if(hb.getHealthState() == HumanBeing.Healthstate.SICK) {
                numberOfIll++;
                hb.incrementSickDays();
            }
            if(hb.checkIfRecovered(mUserInput.getSickDaysMin(),mUserInput.getSickDaysMax()))
                numberOfRecovered++;
            if(hb.checkIfDied(mUtil,mUserInput.getDeathProbability()))
                numberOfDied++;
        }
        numberOfInfectedAccumulated += numberOfGetInfected;
        numberOfDeadAccumulated += numberOfDied;
        mUtil.reportResult(mUserInput, numberOfInfectedAccumulated, numberOfDeadAccumulated,
                numberOfIll, numberOfGetInfected ,
                numberOfDied, numberOfRecovered);
        return numberOfIll > 0;
    }
}
