package by.epamlab.model;

import java.util.concurrent.Callable;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

/**
 * Class Cloud is necessary so that the tourist can reset the photos.
 * - implements - Callable.
 * @author Valentin Velichko
 * */
public class Cloud implements Callable<Integer> {

    /**
     * This field is used for sharing photos.
     * */
    public static Exchanger<Integer> FOTO_EXCHANGER;

    /**
     * This field is used for the correct exchange of photos.
     * */
    public static final Semaphore SEMAPHORE_PHOTO = new Semaphore(1);

    /**
     * Constructor - creating a new object.
     * */
    public Cloud() {
        FOTO_EXCHANGER = new Exchanger<>();
    }

    @Override
    public Integer call() throws Exception {
        int totalFotoNumber = 0;
        for (int i = 0; i < Bus.NUMBER_OF_SEATS; i++) {
            totalFotoNumber += FOTO_EXCHANGER.exchange(0);
        }
        return totalFotoNumber;
    }

    /**
     * This method is used so that the tourist can drop a photo on the cloud.
     * @param tourist - tourist.
     * */
    public static void throwFotoOnCloud(Tourist tourist){
        try {
            SEMAPHORE_PHOTO.acquire();
            int zero = FOTO_EXCHANGER.exchange(tourist.getFoto());
            tourist.setFoto(zero);
            SEMAPHORE_PHOTO.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Tourist.log.info( tourist + " had a rest, threw a photo on a cloud and returned to the bus.");
        tourist.getPhaser().arriveAndAwaitAdvance();
    }
}
