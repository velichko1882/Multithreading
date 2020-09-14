package by.epamlab.model.entity;

import by.epamlab.model.consumers.CurrencyExchange;

import java.util.Random;

/**
 * Class Tourist describes the essence of a tourist in a resort town.
 * @author Valentin Velichko.
 * */
public class Tourist {

    public static int counter;

    private int id;

    /**
     * This field contains information about the number of dollars a tourist has.
     * */
    private int dollars;

    /**
     * This field contains information about the number of rubles a tourist has.
     * */
    private int rubles;

    public Tourist(){
        this.id = ++counter;
        this.dollars = 1000;
    }

    public int getDollars() {
        return dollars;
    }

    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public int getRubles() {
        return rubles;
    }

    public void setRubles(int rubles) {
        this.rubles = rubles;
    }

    /**
     * This method allows the tourist to know the dollar exchange rate.
     * */
    public int getExchangeRate(CurrencyExchange currencyExchange){
        return currencyExchange.getRate();
    }

    /*
     * This method describes the logic of deciding how many dollars a tourist wants to exchange.
     * */
    public int getDollarsToExchange(){
        Random random = new Random();
        return random.nextInt(901)+100;
    }

    /*
     * This method describes the logic of exchanging dollars.
     * */
    public void makeExchange(int rate, int dollarsToExchange){
        this.dollars -= dollarsToExchange;
        this.rubles += dollarsToExchange * rate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tourist{");
        sb.append("id=").append(id);
        sb.append(", dollars=").append(dollars);
        sb.append(", rubles=").append(rubles);
        sb.append('}');
        return sb.toString();
    }

}
