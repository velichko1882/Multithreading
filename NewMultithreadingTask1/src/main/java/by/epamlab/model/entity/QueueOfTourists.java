package by.epamlab.model.entity;

import by.epamlab.model.producers.TouristGenerator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class QueueOfTourists describes the queue of tourists to the currency exchange.
 * @author Valentin Velichko.
 * */
public class QueueOfTourists {
    /*
    * This field contains a blocking queue of tourists.
    * */
    private BlockingQueue<Tourist> queue;

    /*
    * This field contains information on which queue the tourist got into.
    * */
    private boolean isCatchTourist;

    /*
    * The default constructor creates a queue with a capacity equal to the maximum number of tourists.
    * */
    public QueueOfTourists() {
        this.queue = new ArrayBlockingQueue<Tourist>(TouristGenerator.numberOfTourists);
    }

    /*
    * The constructor with a parameter creates a queue with a capacity equal to the parameter.
    * */
    public QueueOfTourists(int capacity){
        this.queue = new ArrayBlockingQueue<Tourist>(capacity);
    }

    public BlockingQueue<Tourist> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Tourist> queue) {
        this.queue = queue;
    }

    public boolean isCatchTourist() {
        return isCatchTourist;
    }

    public void setCatchTourist(boolean catchTourist) {
        isCatchTourist = catchTourist;
    }

}
