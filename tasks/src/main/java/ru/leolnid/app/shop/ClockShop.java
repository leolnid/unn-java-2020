package ru.leolnid.app.shop;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.leolnid.app.clock.Clock;
import ru.leolnid.app.clock.ClockException;
import ru.leolnid.app.clock.ClockFabric;
import ru.leolnid.app.clock.ClockType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

        return _clocks.stream().max(Comparator.comparing(Clock::getPrice)).get();
    }

    public List<String> getMarks() {
        return _clocks.stream().map(Clock::getMark).collect(Collectors.toSet()).stream().sorted().collect(Collectors.toList());
    }

    public Collection<Clock> getClocks() {
        return _clocks;
    }

    public void save(File file) throws IOException {
        boolean ok = file.exists() && file.canWrite();
        if (!file.exists())
            ok = file.createNewFile();

        if (!ok) {
            return;
        }

        JSONArray saveData = new JSONArray();
        for (Clock clock: _clocks) {
            JSONObject clockData = new JSONObject();
            clockData.put("Mark", clock.getMark());
            clockData.put("Price", Float.toString(clock.getPrice()));
            clockData.put("Time", Float.toString(clock.getTime()));
            clockData.put("Type", clock.getType().toString());

            saveData.add(clockData);
        }

        Files.writeString(Path.of(file.getPath()), saveData.toJSONString());
    }

    public void load(File file) throws IOException, ParseException, ClockException {
        boolean ok = file.exists() && file.canWrite();
        if (!file.exists())
            ok = file.createNewFile();

        if (!ok) {
            return;
        }

        JSONArray loadData = (JSONArray) new JSONParser().parse(new FileReader(file.getAbsolutePath()));
        System.out.println(loadData);
        _clocks = new HashSet<>();

        for (Object o: loadData) {
            JSONObject clock = (JSONObject) o;
            String mark = (String) clock.get("Mark");
            float price = Float.parseFloat((String) clock.get("Price"));
            float time = Float.parseFloat((String) clock.get("Time"));
            String type = (String) clock.get("Type");

            Clock newClock = ClockFabric.create(ClockType.valueOf(type), mark, price);
            newClock.setTime(time);
            _clocks.add(newClock);
        }
    }
}
