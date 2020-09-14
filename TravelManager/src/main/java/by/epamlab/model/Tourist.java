package by.epamlab.model;

import by.epamlab.model.phases.*;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Phaser;
import java.util.logging.Logger;

/**
* Class Tourist describes the entity of the tourist and his actions during the tour.
* - implements - Runnable.
* @author Valentin Velichko
* */
public class Tourist implements Runnable {

    /**
     * This field is used to display information on the screen.
     * */
    public static Logger log = Logger.getLogger(Tourist.class.getName());

    /**
     * Field counter, used to obtain the unique id-number of a tourist.
     * */
    private static int counter;

    /**
     * This field is used to generate a random wait time.
     * */
    private static Random random = new Random();

    /**
     * Field id-number for tourist.
     * */
    private int id;

    /**
     * This field indicates that the tourist has started the tour.
     * */
    private boolean isInTravel;

    /**
     * This field contains the number of photos the tourist took during the trip.
     * */
    private int foto;

    /**
     * This field contains information about how much time a tourist needs to eat.
     * */
    private int eatingTimeInSec;

    /**
     * Field phaser.
     * */
    private Phaser phaser;

    /**
     * Constructor - creating a new object.
     * @param phaser - phaser.
     * */
    public Tourist(Phaser phaser) {
        this.id = ++counter;
        this.eatingTimeInSec = random.nextInt(5) + 1;
        this.phaser = phaser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInTravel() {
        return isInTravel;
    }

    public void setInTravel(boolean inTravel) {
        isInTravel = inTravel;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getEatingTimeInSec() {
        return eatingTimeInSec;
    }

    public void setEatingTimeInSec(int eatingTimeInSec) {
        this.eatingTimeInSec = eatingTimeInSec;
    }

    /**
     * This method serves to make the tourist take a photo, increases the photo counter by 1.
     * */
    public void makeFoto(){
        foto++;
    }

    public Phaser getPhaser() {
        return phaser;
    }

    public void setPhaser(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tourist{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void run() {
        log.info(this + " arrived at the collection point.");
        try {
            CollectionPoint.DEPARTURE_BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        sleepUntilNextStop();
        Prague.drinkDarkCzechBeer(this);
        sleepUntilNextStop();
        Paris.lookAtTheMonaLizaSmile(this);
        sleepUntilNextStop();
        Rome.visiThemeRestaurant(this);
        sleepUntilNextStop();
        CoastOfSpain.relaxOnTheBeach(this);
        Cloud.throwFotoOnCloud(this);
        sleepUntilNextStop();
        phaser.arriveAndDeregister();
    }

    /**
     * This method allows tourists to relax while the bus travels to the next stop.
     * */
    private void sleepUntilNextStop(){
        phaser.arriveAndAwaitAdvance();
    }

}
