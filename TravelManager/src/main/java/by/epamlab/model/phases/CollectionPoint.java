package by.epamlab.model.phases;

import by.epamlab.model.Bus;
import by.epamlab.model.Tourist;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;

/**
 * Class CollectionPoint describes the logic of tourist and bus behavior in collection point.
 * @author Valentin Velichko.
 * */
public class CollectionPoint {

    /**
     * Field - cyclic barrier for tourists.
     * */
    public static CyclicBarrier DEPARTURE_BARRIER;

    /**
     * This field is used to generate a random wait time.
     * */
    private static Random random = new Random();

    /**
     * This method creates tourists and makes the bus wait until all tourists arrive at the collection point.
     * @param bus - bus.
     * */
    public static void waitArrivalTourists(Bus bus){
        DEPARTURE_BARRIER = new CyclicBarrier(Bus.NUMBER_OF_SEATS);
        for (int i = 0; i < Bus.NUMBER_OF_SEATS; i++) {
            Tourist tourist = new Tourist(bus.getPhaser());
            bus.putTourist(tourist);
            bus.getPhaser().register();
            new Thread(tourist).start();
            try {
                Thread.sleep(random.nextInt(5) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method drops tourists after the trip and prepares the bus for a new tour.
     * @param bus - bus.
     * */
    public static void finishTour(Bus bus){
        bus.dropOffTourists();
        bus.getPhaser().arriveAndDeregister();
        bus.setPhaser(new Phaser());
    }

}
