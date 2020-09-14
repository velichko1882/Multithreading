package by.epamlab.model.entity;

/**
 * Class UnofficialExchange describes an unofficial currency exchange.
 * - extends - CurrencyExchange.
 * @author Valentin Velichko
 * */
public class UnofficialExchange extends CurrencyExchange {

    public UnofficialExchange(ExchangeRates rates) {
        super(rates);
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
}
