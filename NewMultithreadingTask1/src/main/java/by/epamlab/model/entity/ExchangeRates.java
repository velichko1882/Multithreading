package by.epamlab.model.entity;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Class ExchangeRates describes the official dollar exchange rate and its changes.
 * - implements - Runnable.
 * @author Valentin Velichko.
 * */
public class ExchangeRates implements Runnable {

    private static Logger log = Logger.getLogger(ExchangeRates.class.getName());

    /*
    * This field serves as a flag to stop the thread of rate changes.
    * */
    private static boolean stop = false;

    /**
     * This field contains information about the official dollar exchange rate.
     * */
    private AtomicInteger dollarRate;

    private Random random;

    public ExchangeRates(int dollarRate) {
        this.dollarRate = new AtomicInteger(dollarRate);
        this.random = new Random();
    }

    public int getDollarRate() {
        return dollarRate.get();
    }

    /*
    * This method describes the logic of changing the official dollar exchange rate.
    * */
    private void changeDollarRate(){
        int change = random.nextInt(40)-20;
        dollarRate.addAndGet(change);
    }

    /*
    * This method is used to stop the thread of changes in the dollar.
    * */
    public static void stopChanges(){
        stop = true;
    }

    @Override
    public void run() {
        String threadNane = "Change Rate";
        Thread.currentThread().setName(threadNane);
        log.info(threadNane + " start...............................");
        while (!stop){
            changeDollarRate();
            log.info("dollar = " + getDollarRate());
            int pause = (new Random().nextInt(6)+5)*1000;
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        log.info(threadNane + " stopped-------------------------------");
    }
}
