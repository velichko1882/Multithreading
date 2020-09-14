package by.epamlab.model.phases;

import by.epamlab.model.Tourist;

import java.util.Random;

/**
 * Class CoastOfSpain describes the logic of tourist behavior during a visit to Spain.
 * @author Valentin Velichko.
 * */
public class CoastOfSpain {

    /**
     * This field is used to generate a random wait time.
     * */
    private static Random random = new Random();

    /**
     * This field contains the station name.
     * */
    public static final String NAME = "Coast of Spain";

    /**
     * This method describes the logic of tourist behavior during a visit to spanish beach.
     * */
    public static void relaxOnTheBeach(Tourist tourist){
        try {
            Thread.sleep((random.nextInt(5)+1) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tourist.makeFoto();
    }

}
