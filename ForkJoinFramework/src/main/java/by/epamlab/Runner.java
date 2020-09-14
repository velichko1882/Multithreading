package by.epamlab;

import java.util.logging.Logger;

public class Runner {

    private static Logger log = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) {

        long beginTimeMilli = System.currentTimeMillis();
        log.info("Begin time in milliSec = " + beginTimeMilli);

        long res = 0;
        long number = 10_000_000_000L;
        for (long i = 0; i <= number; i++) {
            res += i;
        }

        long endTimeMilli = System.currentTimeMillis();
        log.info("End time in milliSec = " + endTimeMilli);

        long resTimeMilli = endTimeMilli - beginTimeMilli;
        log.info("Result time in milliSec = " + resTimeMilli);
        log.info("Calculation result = " + res);

    }
}
