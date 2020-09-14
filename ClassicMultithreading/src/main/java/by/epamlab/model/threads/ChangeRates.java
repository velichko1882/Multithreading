package by.epamlab.model.threads;

import by.epamlab.model.entity.ExchangeRates;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Class ChangeRates serves to launch a separate thread for changing the official exchange rate.
 * - implements - Runnable.
 * @author Valentin Velichko
 * */
public class ChangeRates implements Runnable {

    private static Logger log = Logger.getLogger(ChangeRates.class.getName());

    private ExchangeRates rates;

    public ChangeRates(ExchangeRates rates) {
        this.rates = rates;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Change Rates");
        log.info(Thread.currentThread().getName() + " start");
        while (!Thread.currentThread().isInterrupted()){
            rates.changeDollarRate();
            log.info("dollar = " + rates.getDollarRate());
            int pause = (new Random().nextInt(6)+5)*1000;
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        log.info("Change Rates interrupted-------------------------------");
    }
}
