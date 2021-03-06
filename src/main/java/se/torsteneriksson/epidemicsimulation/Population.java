package se.torsteneriksson.epidemicsimulation;

import java.util.Iterator;

/**
 * Represents a full population and encapsulate that it is
 * actually represented by a matrix XxY
 */
public class Population implements Iterable {
    final private HumanBeing[][] mPopulation;
    final private Utilities mUtil;
    private int curr_x = 0;
    private int curr_y = 0;

    public Population(HumanBeing[][] hb) {
        mPopulation = hb;
        init();
        mUtil = new Utilities();
    }

    public void init() {
        for(int x = 0; x < mPopulation.length;x++) {
            for (int y = 0; y < mPopulation[x].length ; y++) {
                mPopulation[x][y] = new HumanBeing();
            }
        }
        setInitialState();
    }

    public void setInitialState() {
        mPopulation[mPopulation.length/2][mPopulation.length/2].setHealthState(HumanBeing.Healthstate.SICK);
        mPopulation[mPopulation.length/2][mPopulation.length/2].incrementSickDays();

    }
    @Override
    public Iterator iterator() {
        curr_x = 0;
        curr_y = 0;
        return new Iterator() {
            private int x = 0;
            private int y = 0;

            @Override
            public boolean hasNext() {
                return x < mPopulation.length && y < mPopulation[x].length;
            }

            private void resetIndexes() {
                if (y == mPopulation[x].length) {
                    y = 0;
                    x++;
                }
            }

            @Override
            public HumanBeing next() {
                if (!hasNext()) {
                    return null;
                }
                while (x < mPopulation.length) {
                    while (y < mPopulation[x].length) {
                        HumanBeing hb = mPopulation[x][y];
                        curr_x = x;
                        curr_y = y;
                        y++;
                        resetIndexes();
                        return hb;
                    }
                    x++;
                }
                return null;
            }

            @Override
            public void remove() {
                // do nothing
            }
        };
    }// End method iterator

    public int getSize() {
        return mPopulation.length*mPopulation.length;
    }

    public boolean checkIfBeInfected(int probability) {
        if(!mPopulation[curr_x][curr_y].isImmune() && (mPopulation[curr_x][curr_y].getHealthState()== HumanBeing.Healthstate.HEALTHY)) {
            if(isNeighbourInfected(curr_x,curr_y)) {
                if(mUtil.isHappening(probability)) {
                    mPopulation[curr_x][curr_y].setHealthState(HumanBeing.Healthstate.SICK);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNeighbourInfected(int x, int y) {
        for(int X = x-1;X <= x +1;X++)
            for(int Y = y-1;Y <= y+1;  Y++) {
                if(isValidMatrixPos(X,Y) && !(x == X && y == Y)) {
                    if((mPopulation[X][Y].getHealthState() == HumanBeing.Healthstate.SICK) &&
                            mPopulation[X][Y].getSickDays() > 0 )
                        return true;
                }
            }
        return false;
    }

    private boolean isValidMatrixPos(int x, int y) {
        return (x >= 0) &&
                (x < mPopulation.length) &&
                (y >= 0) &&
                (y < mPopulation[x].length);
    }
}
