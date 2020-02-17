package ferrocarrilesDeAmericaDelSur.railways;

import ferrocarrilesDeAmericaDelSur.errors.RailwaySystemError;
import ferrocarrilesDeAmericaDelSur.errors.SetUpError;
import ferrocarrilesDeAmericaDelSur.tools.Clock;
import ferrocarrilesDeAmericaDelSur.tools.Delay;

/**
 * An implementation of a railway.  The runTrain method, should, in collaboration with Bolivia's runTrain(), guarantee
 * safe joint operation of the railways.
 */
public class Peru extends Railway {
	/**
	 * Change the parameters of the Delay constructor in the call of the superconstructor to
	 * change the behaviour of this railway.
	 * @throws SetUpError if there is an error in setting up the delay.
	 */
	public Peru() throws SetUpError {
		super("Peru",new Delay(0.1,0.3));
	}

    /**
     * Run the train on the railway.
	 * This method currently does not provide any synchronisation to avoid two trains being in the pass at the same time.
     */
	public void runTrain() throws RailwaySystemError {
		Clock clock = getRailwaySystem().getClock();
		Railway nextRailway = getRailwaySystem().getNextRailway(this); // find out from the railway system which is the other railway
		while (!clock.timeOut()) {
			choochoo(); // the non-critical section
			getBasket().putStone(); // put a stone in your basket
			// i.e. signal that you want to cross the pass
			while (nextRailway.getBasket().hasStone()) { // if the other railway's basket has a stone in it
				// i.e. the other railway wants to cross the pass
				getBasket().takeStone(); // take the stone out of your basket
				// i.e. give the other railway a chance to cross the pass
				siesta(); // have a snooze
				getBasket().putStone(); // put the stone back in your basket
				// because you still want to cross the pass
			}
			crossPass(); // the critical section
			getBasket().takeStone(); // take the stone out of your basket
			// because you have now successfully crossed the pass
			// and do not (currently) want to cross it again
		}
	}
}