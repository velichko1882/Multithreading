package by.epamlab.model.entity;

import java.util.Random;

/**
 * Class ExchangeRates describes the official dollar exchange rate and its changes.
 * @author Valentin Velichko.
 * */

public class ExchangeRates {

    /**
     * This field contains information about the official dollar exchange rate.
     * */
    private int dollarRate;

    private Random random;

    public ExchangeRates(int dollarRate) {
        this.dollarRate = dollarRate;
        this.random = new Random();
    }

    public synchronized int getDollarRate() {
        return dollarRate;
    }

    /*
    * This method describes the logic of changing the official dollar exchange rate.
    * */
    public void changeDollarRate(){
        int change = random.nextInt(40)-20;
        synchronized (this){
            dollarRate += change;
        }
    }

}
