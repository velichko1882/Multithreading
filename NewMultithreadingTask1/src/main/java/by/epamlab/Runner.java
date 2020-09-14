package by.epamlab;

import by.epamlab.model.consumers.CurrencyExchange;
import by.epamlab.model.consumers.OfficialExchangeOffice;
import by.epamlab.model.consumers.UnofficialExchange;
import by.epamlab.model.entity.ExchangeRates;
import by.epamlab.model.entity.QueueOfTourists;
import by.epamlab.model.producers.TouristGenerator;

import java.util.concurrent.*;

public class Runner {

    public static void main(String[] args) {

        QueueOfTourists officialExchangeQueue = new QueueOfTourists(3);
        QueueOfTourists unofficialExchangeQueue = new QueueOfTourists();

        ExchangeRates rates = new ExchangeRates(2100);
        TouristGenerator touristGenerator = new TouristGenerator(officialExchangeQueue, unofficialExchangeQueue);
        CurrencyExchange officialExchangeOffice = new OfficialExchangeOffice(rates, officialExchangeQueue);
        CurrencyExchange unofficialExchange = new UnofficialExchange(rates, unofficialExchangeQueue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(rates);
        service.execute(touristGenerator);
        Future<Boolean> official = service.submit(officialExchangeOffice);
        Future<Boolean> unofficial = service.submit(unofficialExchange);
        service.shutdown();
        try {
            boolean isOfficialFinish = official.get();
            boolean isUnofficialFinish = unofficial.get();
            if (isOfficialFinish && isUnofficialFinish){
                ExchangeRates.stopChanges();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }
}
