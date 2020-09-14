package by.epamlab.model.consumers;

import by.epamlab.model.entity.ExchangeRates;
import by.epamlab.model.entity.QueueOfTourists;
import by.epamlab.model.entity.Tourist;
import by.epamlab.model.producers.TouristGenerator;

import java.util.logging.Logger;

/**
 * Class UnofficialExchange describes an unofficial currency exchange.
 * - extends - CurrencyExchange.
 * @author Valentin Velichko
 * */
public class UnofficialExchange extends CurrencyExchange {

    private static Logger log = Logger.getLogger(UnofficialExchange.class.getName());

    public UnofficialExchange(ExchangeRates rates, QueueOfTourists tourists) {
        super(rates, tourists);
    }

    /*
    * This method contains the logic of obtaining an unofficial dollar exchange rate.
    * */
    @Override
    public int getRate() {
        int rate = super.getRate();
        double bonus = (double)rate / 100 * 5;
        return (int) (rate - bonus);
    }

    @Override
    public Boolean call() throws Exception {
        String threadName = "Unofficial Exchange";
        Thread.currentThread().setName(threadName);
        log.info(threadName + " start................................");
        boolean isFinish = false;
        while (!isFinish){
            Tourist tourist = getTourist();
            if (tourist != null){
                int rate = tourist.getExchangeRate(this);
                exchange(tourist, rate);
                log.info("Tourist from " + threadName + ": " + tourist + "; dollar=" + rate);
                Thread.sleep(2000);
            }else {
                if (Tourist.counter == TouristGenerator.numberOfTourists){
                    isFinish = true;
                }
            }
        }
        log.info(threadName + " is finish----------------------------");
        return isFinish;

    }
}
