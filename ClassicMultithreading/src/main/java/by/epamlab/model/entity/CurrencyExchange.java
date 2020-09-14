package by.epamlab.model.entity;

import by.epamlab.model.threads.TouristGenerator;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Class CurrencyExchange describes a currency exchange office for tourists.
 * @author Valentin Velichko.
 * */

public abstract class CurrencyExchange {

    /*
    * This field contains information on exchange rates.
    * */
    private ExchangeRates rates;

    /*
    * This field contains the queue of tourists to the exchange office.
    * */
    private Queue<Tourist> tourists;

    /*
    * This field contains information on whose queue the next tourist was.
    * */
    private boolean isCatchTourist;

    public CurrencyExchange(ExchangeRates rates) {
        this.rates = rates;
        this.tourists = new ArrayDeque<>();
    }

    public ExchangeRates getRates() {
        return rates;
    }

    public Queue<Tourist> getTourists() {
        return tourists;
    }

    public boolean isCatchTourist() {
        return isCatchTourist;
    }

    public void setCatchTourist(boolean catchTourist) {
        isCatchTourist = catchTourist;
    }

    /*
    * This method adds a tourist to the queue.
    * */
    public synchronized boolean addTourist(Tourist tourist){
        boolean isAdded = tourists.add(tourist);
        notify();
        return isAdded;
    }

    /*
    * This method contains the logic of getting a tourist from the queue.
    * */
    public synchronized Tourist getTourist(){
        if (tourists.size() == 0){
            if (Tourist.counter < TouristGenerator.numberOfTourists) {
                try {
                    wait(21000);  //the waiting time is 21 seconds, because according to the conditions
                                         // of the task, tourists appear in the city with a frequency of 1 to 10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                return null;
            }
        }
        if (tourists.size() == 0){
            return null;
        }
        return tourists.remove();
    }

    /*
    * This method contains the logic for obtaining the official exchange rate.
    * */
    public int getRate(){
        return rates.getDollarRate();
    }

    /*
    * This method contains the currency exchange logic.
    * */
    public void exchange(Tourist tourist, int rate){
        int dollarsToExchange = tourist.getDollarsToExchange();
        tourist.makeExchange(rate, dollarsToExchange);
    }
}
