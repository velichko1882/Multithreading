package by.epamlab.model.consumers;

import by.epamlab.model.entity.ExchangeRates;
import by.epamlab.model.entity.QueueOfTourists;
import by.epamlab.model.entity.Tourist;
import by.epamlab.model.producers.TouristGenerator;

import java.util.logging.Logger;

/**
 * Class OfficialExchangeOffice describes an official currency exchange office.
 * - extends - CurrencyExchange.
 * @author Valentin Velichko
 * */
public class OfficialExchangeOffice extends CurrencyExchange {
    private static Logger log = Logger.getLogger(OfficialExchangeOffice.class.getName());

    public OfficialExchangeOffice(ExchangeRates rates, QueueOfTourists tourists) {
        super(rates, tourists);
    }

    @Override
    public Boolean call() throws Exception {
        String threadName = "Official Exchange";
        Thread.currentThread().setName(threadName);
        log.info(threadName + " start................................");
        boolean isFinish = false;
        while (!isFinish){
            Tourist tourist = getTourist();
            if (tourist != null){
                int rate = tourist.getExchangeRate(this);
                exchange(tourist, rate);
                log.info("Tourist from " + threadName + ": " + tourist + "; dollar=" + rate);
                Thread.sleep(3000);
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
