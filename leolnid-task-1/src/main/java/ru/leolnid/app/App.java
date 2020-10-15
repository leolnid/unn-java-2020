package ru.leolnid.app;

import ru.leolnid.app.clock.*;

/**
 * @author Leonid Dyukov <leonid.dyukov@gmail.com>
 * @version 0.0.1
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            Clock a = ClockFabric.create(ClockType.CLOCK_WITH_SECONDS, "Mark1");
            a.setTime(100);
            System.out.println(a.getHours() + ":" + a.getMinutes() + ":" + a.getSeconds());
        } catch (ClockException e) {
            System.out.println(e.getMessage());
        }
    }
}
