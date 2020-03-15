package ferrocarrilesDeAmericaDelSur.railways;

import ferrocarrilesDeAmericaDelSur.errors.RailwaySystemError;
import ferrocarrilesDeAmericaDelSur.errors.SetUpError;
import ferrocarrilesDeAmericaDelSur.tools.Clock;
import ferrocarrilesDeAmericaDelSur.tools.Delay;

/**
 * An implementation of a railway.  The runTrain method, should, in collaboration with Peru's runTrain(), guarantee
 * safe joint operation of the railways.
 */
public class Bolivia extends Railway {
	/**
     * Change the parameters of the Delay constructor in the call of the superconstructor to
	 * change the behaviour of this railway.
	 * @throws SetUpError if there is an error in setting up the delay.
	 */
	public Bolivia() throws SetUpError {
		super("Bolivia",new Delay(0.1,0.3));
	}

    /**
     * Run the train on the railway.
     * This method currently does not provide any synchronisation to avoid two trains being in the pass at the same time.
     */
	public void runTrain() throws RailwaySystemError {
		Clock clock = getRailwaySystem().getClock(); //Get the clock
		Railway nextRailway = getRailwaySystem().getNextRailway(this); //Get the next railway
		while (!clock.timeOut()) { //whilst the clock is running
			choochoo(); //move the train around the track until it reaches the pass
			getBasket().putStone();//put the stone in the basket
			while (nextRailway.getBasket().hasStone()) { //whilst the other basket has a stone
				if (!getSharedBasket().hasStone()) {//check the turn
					getBasket().takeStone(); //remove the stone from the basket
					while (!getSharedBasket().hasStone()){ //when there is no stone in the shared basket
						siesta(); //wait
					}
					getBasket().putStone(); //put the stone in the basket
				}
			}
			crossPass(); //the critical section
			getSharedBasket().takeStone();//set the turn
			getBasket().takeStone();//take the stone from the basket
		}
	}
}