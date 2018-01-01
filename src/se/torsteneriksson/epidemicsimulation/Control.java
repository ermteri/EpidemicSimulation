package se.torsteneriksson.epidemicsimulation;

/**
 * Responsible for execution of the simulation
 */
public class Control {
    private HumanBeing [][] mPopulation;
    private int numberOfInfectedAccumulated;
    private int numberOfDeadAccumulated;
    private UserInput mUserInput;
    private Utilities mUtil = new Utilities();

    public Control(UserInput userInput){
        this.mUserInput = userInput;
    }

    public void init() {
        mPopulation = new HumanBeing[mUserInput.getPopulationSize()][mUserInput.getPopulationSize()];
        for(int y = 0; y < mUserInput.getPopulationSize(); y++) {
            for (int x = 0; x < mUserInput.getPopulationSize(); x++) {
                mPopulation[x][y] = new HumanBeing();
            }
        }
        mPopulation[mUserInput.getPopulationSize()/2][mUserInput.getPopulationSize()/2].setHealthState(HumanBeing.Healthstate.SICK);
    }
    public void startSimulation() {
        System.out.println("Simulate started on population with " +
                mUserInput.getPopulationSize()*mUserInput.getPopulationSize() + " inhabitants");
        int numberOfDays = 0;
        numberOfInfectedAccumulated = 0;
        numberOfDeadAccumulated = 0;
        while(loopOneDay()) {
            numberOfDays++;
        }
        System.out.println("Simulation done after " + numberOfDays + " days");

    }

    private boolean loopOneDay() {
        int numberOfIll = 0;
        int numberOfGetInfected = 0;
        int numberOfDied = 0;
        int numberOfRecovered = 0;
        for(int y = 0; y < mUserInput.getPopulationSize(); y++) {
            for(int x = 0; x < mUserInput.getPopulationSize(); x++) {
                if(checkIfBeInfected(x,y))
                    numberOfGetInfected++;
                if(mPopulation[x][y].getHealthState() == HumanBeing.Healthstate.SICK) {
                    numberOfIll++;
                    mPopulation[x][y].incremecntSickDays();
                 }
                if(mPopulation[x][y].checkIfRecovered(mUserInput.getSickDaysMin(),mUserInput.getSickDaysMax()))
                    numberOfRecovered++;
                if(mPopulation[x][y].checkIfDied(mUtil,mUserInput.getDeathProbability()))
                    numberOfDied++;
                //System.out.printf("%d,",numberOfIll);
            }
        }
        numberOfInfectedAccumulated += numberOfGetInfected;
        numberOfDeadAccumulated += numberOfDied;
        System.out.println("InfectedAccu:" + numberOfInfectedAccumulated +
                ", Dead accu:" + numberOfDeadAccumulated+
                ", Ill:" + numberOfIll +
                ", Infected:"+numberOfGetInfected +
                ", Died:" + numberOfDied +
                ", Recovered:"+ numberOfRecovered);
        return numberOfIll > 0;
    }

    private boolean checkIfBeInfected(int x,int y) {
        if(!mPopulation[x][y].isImune()) {
            if(isNeighbourInfected(x,y)) {
                if(mUtil.isHappening(mUserInput.getContaminationProbability())) {
                    mPopulation[x][y].setHealthState(HumanBeing.Healthstate.SICK);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNeighbourInfected(int x, int y) {
        for(int X = x-1;X <= x +1;X++)
            for(int Y = y-1;Y <= y+1;  Y++) {
                if(isValidMatrixPos(X,Y) && x != X && y != Y) {
                    if((mPopulation[X][Y].getHealthState() == HumanBeing.Healthstate.SICK) &&
                            mPopulation[X][Y].getSickDays() > 0 )
                        return true;
                }
            }
            return false;
    }

    private boolean isValidMatrixPos(int x, int y) {
        if((x >= 0) &&
                (x < mUserInput.getPopulationSize())&&
                (y >= 0) &&
                (y < mUserInput.getPopulationSize()))
            return true;
        else
            return false;
    }


}
