package by.epamlab;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

public class RunnerForkJoin {

    private static Logger log = Logger.getLogger(RunnerForkJoin.class.getName());

    private static final long NUMBER_FOR_CALCULATE = 10_000_000_000L;
    private static final int NUMBER_OF_THREADS = 16;


    public static void main(String[] args) {
        long beginTimeMilli = System.currentTimeMillis();
        log.info("Begin time in milliSec = " + beginTimeMilli);
        ForkJoinPool pool = new ForkJoinPool(NUMBER_OF_THREADS);
        long result = pool.invoke(new RecursiveCalc(0, NUMBER_FOR_CALCULATE));

        long endTimeMilli = System.currentTimeMillis();
        log.info("End time in milliSec = " + endTimeMilli);

        long resTimeMilli = endTimeMilli - beginTimeMilli;
        log.info("Result time in milliSec = " + resTimeMilli);
        log.info("Calculation result = " + result);
    }


    private static class RecursiveCalc extends RecursiveTask<Long> {

        private long start;
        private long end;

        public RecursiveCalc(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if ((end - start) <= NUMBER_FOR_CALCULATE / NUMBER_OF_THREADS){
                long res = 0;
                for (long i = start; i <= end; i++) {
                    res += i;
                }
                return res;
            } else {
                long middle = (start + end) / 2;
                RecursiveCalc left = new RecursiveCalc(start, middle);
                RecursiveCalc right = new RecursiveCalc(middle + 1, end);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }

}
