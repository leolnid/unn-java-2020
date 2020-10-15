package ru.leolnid.app.clock;

public class ClockWithoutSeconds extends ClockWithSeconds {
    ClockWithoutSeconds(String mark, float price) throws ClockException {
        super(mark, price);
    }

    @Override
    public float getSeconds() {
        return 0;
    }
}
