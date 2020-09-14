package by.epamlab.model.entity;

/**
 * Class OfficialExchangeOffice describes an official currency exchange office.
 * - extends - CurrencyExchange.
 * @author Valentin Velichko
 * */
public class OfficialExchangeOffice extends CurrencyExchange {

    /*
    * This field contains information about the maximum number of tourists in the queue.
    * */
    private int maxQueueCapacity;

    public OfficialExchangeOffice(ExchangeRates rates, int maxQueueCapacity) {
        super(rates);
        this.maxQueueCapacity = maxQueueCapacity;
    }

    public int getMaxQueueCapacity() {
        return maxQueueCapacity;
    }

    public void setMaxQueueCapacity(int maxQueueCapacity) {
        this.maxQueueCapacity = maxQueueCapacity;
    }

}
