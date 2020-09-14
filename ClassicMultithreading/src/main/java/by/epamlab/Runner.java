package by.epamlab;

import by.epamlab.model.entity.ExchangeRates;
import by.epamlab.model.entity.OfficialExchangeOffice;
import by.epamlab.model.entity.UnofficialExchange;
import by.epamlab.model.threads.ChangeRates;
import by.epamlab.model.threads.TouristGenerator;
import by.epamlab.model.threads.TouristService;

public class Runner {

    public static void main(String[] args) {

        ExchangeRates rates = new ExchangeRates(2087);
        OfficialExchangeOffice officialExchangeOffice = new OfficialExchangeOffice(rates, 5);
        UnofficialExchange unofficialExchange = new UnofficialExchange(rates);

        Thread changesRates = new Thread(new ChangeRates(rates));
        Thread touristGenerator = new Thread(new TouristGenerator(officialExchangeOffice,
                unofficialExchange));
        Thread officialTouristService = new Thread(new TouristService("Official Exchange",
                officialExchangeOffice, 3000));
        Thread unofficialTouristService = new Thread(new TouristService("Unofficial Exchange",
                unofficialExchange, 2000));

        changesRates.start();
        touristGenerator.start();
        officialTouristService.start();
        unofficialTouristService.start();

        try {
            officialTouristService.join();
            unofficialTouristService.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changesRates.interrupt();

    }
}
