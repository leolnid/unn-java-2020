package ru.leolnid.app.shop;

import ru.leolnid.app.clock.Clock;
import ru.leolnid.app.clock.ClockException;

import java.util.*;
import java.util.stream.Collectors;

public class ClockShop {
    private Collection<Clock> _clocks = new HashSet<>();

    public boolean addClock(Clock clock) {
        return _clocks.add(clock);
    }

    public boolean removeClock(Clock clock) {
        return _clocks.remove(clock);
    }

    public void setTime(float time) throws ClockException {
        for (Clock clock: _clocks) {
            clock.setTime(time);
        }
    }

    public void addTime(float time) throws ClockException {
        for (Clock clock: _clocks) {
            clock.addTime(time);
        }
    }

    public Clock getMostExpensiveClock() throws ShopException {
        if (_clocks.size() == 0)
            throw new ShopException("No valid clock in shop");

        return  _clocks.stream().max(Comparator.comparing(Clock::getPrice)).get();
    }

    public List<String> getMarks() {
        return _clocks.stream().map(Clock::getMark).collect(Collectors.toSet()).stream().sorted().collect(Collectors.toList());
    }
}
