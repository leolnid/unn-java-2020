package ru.leolnid.app.clock;

public class ClockWithoutSeconds extends ClockWithSeconds {
    ClockWithoutSeconds(String mark, float price) throws ClockException {
        super(mark, price);
    }

    @Override
    public int getSeconds() {
        return 0;
    }

    @Override
    public ClockType getType() {
        return ClockType.CLOCK_WITHOUT_SECONDS;
    }
}
