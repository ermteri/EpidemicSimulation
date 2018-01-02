package se.torsteneriksson.epidemicsimulation;

import java.util.Iterator;

/**
 * Represents a full population and encapsulate that it is
 * actually representad by a matrix XxY
 */
public class Population implements Iterable {
    private HumanBeing[][] mPopulation;
    private Utilities mUtil;
    private int curr_x = 0;
    private int curr_y = 0;

    public Population(HumanBeing[][] hb) {
        mPopulation = hb;
        for(int x = 0; x < hb.length;x++) {
            for (int y = 0; y < hb[x].length ; y++) {
                mPopulation[x][y] = new HumanBeing();
            }
        }
        mPopulation[hb.length/2][hb.length/2].setHealthState(HumanBeing.Healthstate.SICK);
        mPopulation[hb.length/2][hb.length/2].incremecntSickDays();
        mUtil = new Utilities();

    }
    @Override
    public Iterator iterator() {
        curr_x = 0;
        curr_y = 0;
        Iterator it = new Iterator() {
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
        return it;
    }// End method iterator

    public int getSize() {
        return mPopulation.length*mPopulation.length;
    }

    public boolean checkIfBeInfected(int probability) {
        if(!mPopulation[curr_x][curr_y].isImune() && (mPopulation[curr_x][curr_y].getHealthState()== HumanBeing.Healthstate.HEALTHY)) {
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
