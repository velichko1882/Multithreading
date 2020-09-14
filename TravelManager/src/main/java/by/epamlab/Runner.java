package by.epamlab;

import by.epamlab.model.Bus;

public class Runner {

    public static void main(String[] args) {

        Bus bus = new Bus();
        new Thread(bus).start();
     }
}
