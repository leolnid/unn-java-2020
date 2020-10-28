package ru.leolnid.app;

import ru.leolnid.app.clock.*;
import ru.leolnid.app.shop.ClockShop;

/**
 * @author Leonid Dyukov <leonid.dyukov@gmail.com>
 * @version 0.0.1
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            ClockShop shop = new ClockShop();
            shop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark1", 100));
            shop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark2", 10));
            shop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark3", 10));
            shop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark4", 10));
            shop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark2", 1000));
            shop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark2", 100));
            shop.addClock(ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark1", 100));

            System.out.println(shop.getMarks());
            System.out.println(shop.getMostExpensiveClock().getPrice());


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
