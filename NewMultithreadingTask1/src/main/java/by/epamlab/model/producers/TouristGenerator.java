package by.epamlab.model.producers;

import by.epamlab.model.entity.ExchangeRates;
import by.epamlab.model.entity.QueueOfTourists;
import by.epamlab.model.entity.Tourist;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Class TouristGenerator serves to create and distribute tourists in a separate queue.
 * - implements - Runnable.
 * @author Valentin Velichko
 * */
public class TouristGenerator implements Runnable {

    private static Logger log = Logger.getLogger(TouristGenerator.class.getName());

    /*
    * This field contains information about the number of tourists who will visit the city.
    * */
    public static int numberOfTourists = 10;

    /*
    * This field contains information about the queue to the official currency exchange office.
    * */
    private QueueOfTourists officialExchangeQueue;

    /*
    * This field contains information about the queue for an unofficial currency exchange.
    * */
    private QueueOfTourists unofficialExchangeQueue;

    private Random random;

    public TouristGenerator(QueueOfTourists officialExchangeQueue, QueueOfTourists unofficialExchangeQueue) {
        this.officialExchangeQueue = officialExchangeQueue;
        this.unofficialExchangeQueue = unofficialExchangeQueue;
        this.random = new Random();
    }

    /*
    * This method describes the logic of creating tourists and their distribution in the queue
    * of an official exchange office and an unofficial one.
    * */
    public void addTouristInQueue(){
        if (!officialExchangeQueue.isCatchTourist() &&
                officialExchangeQueue.getQueue().remainingCapacity() > 0){
            officialExchangeQueue.setCatchTourist(officialExchangeQueue.getQueue().add(new Tourist()));
            unofficialExchangeQueue.setCatchTourist(false);
        }else {
            unofficialExchangeQueue.setCatchTourist(unofficialExchangeQueue.getQueue().add(new Tourist()));
            officialExchangeQueue.setCatchTourist(false);
        }
    }

    @Override
    public void run() {
        String threadName = "Tourist generator";
        Thread.currentThread().setName(threadName);
        log.info(threadName + " start.................................");
        while (Tourist.counter < numberOfTourists){
            addTouristInQueue();
            log.info("Add tourist #" + Tourist.counter);
            try {
                Thread.sleep((random.nextInt(10) + 1) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info(threadName + " is finish----------------------------------");
    }
}
