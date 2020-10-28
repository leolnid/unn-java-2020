package ru.leolnid.app.clock;

public class ClockFabric {
    public static Clock create(ClockType type, String mark) throws ClockException {
        switch (type) {
            case CLOCK_WITH_SECONDS:
                return new ClockWithSeconds(mark, 0);
            case CLOCK_WITHOUT_SECONDS:
                return new ClockWithoutSeconds(mark, 0);
        }
        throw new ClockException("Invalid clock type: " + type.name());
    }

    public static Clock create(ClockType type, String mark, float price) throws ClockException {
        switch (type) {
            case CLOCK_WITH_SECONDS:
                return new ClockWithSeconds(mark, price);
            case CLOCK_WITHOUT_SECONDS:
                return new ClockWithoutSeconds(mark, price);
        }
        throw new ClockException("Invalid clock type: " + type.name());
    }
}
