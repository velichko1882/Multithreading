package by.epamlab.model.threads;

import by.epamlab.model.entity.CurrencyExchange;
import by.epamlab.model.entity.Tourist;

import java.util.logging.Logger;

/**
 * Class TouristService serves to create separate threads for the work of an official
 * exchange office and an unofficial one.
 * - implements - Runnable.
 * @author Valentin Velichko
 * */
public class TouristService implements Runnable {

    private static Logger log = Logger.getLogger(TouristService.class.getName());

    /*
    * This field contains information about the name of the thread.
    * */
    private String name;

    private CurrencyExchange currencyExchange;

    /*
    * Time to serve one tourist.
    * */
    private int serviceTime;

    public TouristService(String name, CurrencyExchange exchange, int serviceTime) {
        this.name = name;
        this.currencyExchange = exchange;
        this.serviceTime = serviceTime;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        String threadName = Thread.currentThread().getName();
        while (true) {
            Tourist tourist = currencyExchange.getTourist();
            if (tourist != null) {
                int rate = tourist.getExchangeRate(currencyExchange);
                currencyExchange.exchange(tourist, rate);
                log.info("Tourist from " + threadName + " , rate = " + rate + " : " + tourist.toString());
                try {
                    Thread.sleep(serviceTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                break;
            }
        }
        log.info(threadName + " interrupted--------------------------------");
    }
}
