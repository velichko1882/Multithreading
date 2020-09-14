package by.epamlab.model.phases;

import by.epamlab.model.Tourist;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Class Rome describes the logic of tourist behavior during a visit to Rome.
 * @author Valentin Velichko.
 * */
public class Rome {

    /**
     * This field contains the station name.
     * */
    public static final String NAME = "Rome";

    /**
     * Field - cyclic barrier for even tourists.
     * */
    private static final CyclicBarrier BARRIER_EVEN = new CyclicBarrier(5);

    /**
     * Field - cyclic barrier for odd tourists.
     * */
    private static final CyclicBarrier BARRIER_ODD = new CyclicBarrier(5);

    /**
     * This method describes the logic of tourist behavior during a visit to restaurant.
     * */
    public static void visiThemeRestaurant (Tourist tourist){
        try {
            if (tourist.getId()%2 == 0){
                BARRIER_EVEN.await();
                Thread.sleep(tourist.getEatingTimeInSec() * 1000);
                Tourist.log.info(tourist + " tasted the best PIZZA in Rome and returned to the bus.");
            } else {
                BARRIER_ODD.await();
                Thread.sleep(tourist.getEatingTimeInSec() * 1000);
                Tourist.log.info(tourist + " tasted the best PASTA in Rome and returned to the bus.");
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        tourist.makeFoto();
        tourist.getPhaser().arriveAndAwaitAdvance();
    }


}
