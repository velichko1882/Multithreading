package by.epamlab.model;

import by.epamlab.model.phases.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Phaser;
import java.util.logging.Logger;

/**
 * Class Bus describes the entity of the bus and his actions during the tour.
 * - implements - Runnable.
 * @author Valentin Velichko
 * */
public class Bus implements Runnable{

    /**
     * This field is used to display information on the screen.
     * */
    private static Logger log = Logger.getLogger(Bus.class.getName());

    /**
     * This field contains an informational message.
     * */
    private final String DEPARTURE = "All the tourists arrived and the bus hit the road.\n";

    /**
     * This field contains information about the number of seats on the bus.
     * */
    public static final int NUMBER_OF_SEATS = 40;

    /**
     * This field contains all the tourists on the bus.
     * */
    private Set<Tourist> tourists;

    /**
     * Field - phaser.
     * */
    private Phaser phaser;

    /**
     * Constructor - creating a new object.
     * */
    public Bus() {
        this.phaser = new Phaser();
        this.tourists = new HashSet<>();
    }

    public Set<Tourist> getTourists() {
        return tourists;
    }

    public void setTourists(Set<Tourist> tourists) {
        this.tourists = tourists;
    }

    public Phaser getPhaser() {
        return phaser;
    }

    public void setPhaser(Phaser phaser) {
        this.phaser = phaser;
    }

    /**
    * This method is used for boarding a tourist on a bus.
    * @param tourist - tourist.
    * */
    public void putTourist(Tourist tourist){
        tourists.add(tourist);
        tourist.setInTravel(true);
    }

    /**
     * This method is used to disembark tourists from the bus at the end of the tour.
     * */
    public void dropOffTourists(){
        for (Tourist t:tourists) {
            t.setInTravel(false);
        }
        tourists.clear();
    }

    @Override
    public void run() {
        for (int i = 1; i < 3; i++) {
            phaser.register();
            CollectionPoint.waitArrivalTourists(this);
            log.info(DEPARTURE);
            goToNextStop();
            waitTourists(Prague.NAME);
            log.info(DEPARTURE);
            goToNextStop();
            waitTourists(Paris.NAME);
            log.info(DEPARTURE);
            goToNextStop();
            waitTourists(Rome.NAME);
            log.info(DEPARTURE);
            goToNextStop();
            log.info("The bus arrived in " + CoastOfSpain.NAME + " and is waiting for tourists.--------------------");
            FutureTask<Integer> cloud = new FutureTask<>(new Cloud());
            new Thread(cloud).start();
            phaser.arriveAndAwaitAdvance();
            try {
                log.info("********* Total number of photos = " + cloud.get() + " ************");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            goToNextStop();
            CollectionPoint.finishTour(this);
            log.info("//////////////   Finish tour for group " + i + " ///////////////////\n\n");
        }
    }

    /**
     * This method is used to move the bus to the next stop.
     * */
    private void goToNextStop(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.arriveAndAwaitAdvance();
    }

    /**
     * This method is used to wait for tourists.
     * @param nameStation - station name.
     * */
    private void waitTourists(String nameStation){
        log.info("The bus arrived in " + nameStation + " and is waiting for tourists.--------------------");
        phaser.arriveAndAwaitAdvance();
    }
}
