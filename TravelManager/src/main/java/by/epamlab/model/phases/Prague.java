package by.epamlab.model.phases;

import by.epamlab.model.Tourist;

import java.util.concurrent.Semaphore;

/**
 * Class Prague describes the logic of tourist behavior during a visit to Prague.
 * @author Valentin Velichko.
 * */
public class Prague {

    /**
     * This field contains the station name.
     * */
    public static final String NAME = "Prague";

    /**
     * Field semaphore.
     * */
    private static final Semaphore SEMAPHORE_BEER = new Semaphore(1, true);

    /**
     * This method describes the logic of tourist behavior during a visit to czech bar.
     * */
    public static void drinkDarkCzechBeer(Tourist tourist){
        try {
            SEMAPHORE_BEER.acquire();
            Thread.sleep(tourist.getEatingTimeInSec() * 1000);
            SEMAPHORE_BEER.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tourist.makeFoto();
        Tourist.log.info( tourist + " drank dark Czech beer and returned to the bus.");
        tourist.getPhaser().arriveAndAwaitAdvance();
    }

}
