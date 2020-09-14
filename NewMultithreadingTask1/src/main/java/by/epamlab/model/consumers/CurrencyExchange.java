package by.epamlab.model.consumers;

import by.epamlab.model.entity.ExchangeRates;
import by.epamlab.model.entity.QueueOfTourists;
import by.epamlab.model.entity.Tourist;
import by.epamlab.model.producers.TouristGenerator;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Class CurrencyExchange describes a currency exchange office for tourists.
 * - implements - Callable.
 * @author Valentin Velichko.
 * */
public abstract class CurrencyExchange implements Callable<Boolean> {

    /*
    * This field contains information on exchange rates.
    * */
    private ExchangeRates rates;

    /*
    * This field contains the queue of tourists to the exchange office.
    * */
    private QueueOfTourists tourists;

    public CurrencyExchange(ExchangeRates rates, QueueOfTourists tourists) {
        this.rates = rates;
        this.tourists = tourists;
    }

    public ExchangeRates getRates() {
        return rates;
    }

    public QueueOfTourists getTourists() {
        return tourists;
    }

    /*
    * This method contains the logic of getting a tourist from the queue.
    * */
    public Tourist getTourist() throws InterruptedException {
        if (Tourist.counter < TouristGenerator.numberOfTourists-1){
            return tourists.getQueue().take();
        }else {
            return tourists.getQueue().poll(10, TimeUnit.SECONDS);
        }
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
