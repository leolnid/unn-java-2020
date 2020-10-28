package ru.leolnid.app.clock;

public class ClockWithSeconds implements Clock {
    private String _mark;
    private float _price;
    private float _currentTime;

    ClockWithSeconds(String mark, float price) throws ClockException {
        _currentTime = 0;
        _mark = mark;

        if (price < 0)
            throw new ClockException("Invalid price: " + price);
        _price = price;
    }

    @Override
    public float getSeconds() {
        return _currentTime % (60);
    }

    @Override
    public float getMinutes() {
        return _currentTime / 60 % 60;
    }

    @Override
    public float getHours() {
        return _currentTime / (60 * 60);
    }

    @Override
    public void setTime(float time) throws ClockException {
        if (time < 0)
            throw new ClockException("Invalid argument: " + time);
        _currentTime = time;
    }

    @Override
    public void addTime(float time) throws ClockException {
        if (time < 0)
            throw new ClockException("Invalid argument: " + time);
        _currentTime += time;
    }

    @Override
    public void setPrice(float price) throws ClockException {
        if (price < 0)
            throw new ClockException("Invalid price: " + price);
        _price = price;
    }

    @Override
    public float getPrice() {
        return _price;
    }

    public String getMark() {
        return _mark;
    }
}
