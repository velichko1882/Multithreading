package by.epamlab.model.threads;

import by.epamlab.model.entity.OfficialExchangeOffice;
import by.epamlab.model.entity.Tourist;
import by.epamlab.model.entity.UnofficialExchange;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Class TouristGenerator serves to create and distribute tourists in a separate stream.
 * - implements - Runnable.
 * @author Valentin Velichko
 * */
public class TouristGenerator implements Runnable {

    private static Logger log = Logger.getLogger(TouristGenerator.class.getName());

    /*
    * This field contains information about the number of tourists who will visit the city.
    * */
    public static int numberOfTourists = 30;

    private OfficialExchangeOffice officialExchangeOffice;
    private UnofficialExchange unofficialExchange;
    private Random random;

    public TouristGenerator(OfficialExchangeOffice officialExchangeOffice,
                            UnofficialExchange unofficialExchange) {
        this.officialExchangeOffice = officialExchangeOffice;
        this.unofficialExchange = unofficialExchange;
        this.random = new Random();
    }

    /*
    * This method describes the logic of creating tourists and their distribution in the queue
    * of an official exchange office and an unofficial one.
    * */
    public void getAndAddTourist(){
        if (!officialExchangeOffice.isCatchTourist() &&
                officialExchangeOffice.getTourists().size() < officialExchangeOffice.getMaxQueueCapacity()){
            officialExchangeOffice.setCatchTourist(officialExchangeOffice.addTourist(new Tourist()));
            unofficialExchange.setCatchTourist(false);
        }else {
            unofficialExchange.setCatchTourist(unofficialExchange.addTourist(new Tourist()));
            officialExchangeOffice.setCatchTourist(false);
        }
    }

    @Override
    public void run() {
        while (Tourist.counter < numberOfTourists){
            getAndAddTourist();
            log.info("Add tourist #" + Tourist.counter);
            try {
                Thread.sleep((random.nextInt(10) + 1) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("Generator is finish----------------------------------");
    }
}
