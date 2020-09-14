package by.epamlab.model.phases;

import by.epamlab.model.Tourist;

import java.util.concurrent.Semaphore;

/**
 * Class Paris describes the logic of tourist behavior during a visit to Paris.
 * @author Valentin Velichko.
 * */
public class Paris {

    /**
     * This field contains the station name.
     * */
    public static final String NAME = "Paris";

    /**
     * Field semaphore.
     * */
    private static final Semaphore SEMAPHORE_LOUVRE = new Semaphore(5, true);

    /**
     * This method describes the logic of tourist behavior during a visit to Louvre.
     * */
    public static void lookAtTheMonaLizaSmile(Tourist tourist){
        try {
            SEMAPHORE_LOUVRE.acquire();
            Thread.sleep(1000);
            SEMAPHORE_LOUVRE.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tourist.makeFoto();
        Tourist.log.info( tourist + " looked at the Mona Lisa smile and returned to the bus.");
        tourist.getPhaser().arriveAndAwaitAdvance();
    }

}
