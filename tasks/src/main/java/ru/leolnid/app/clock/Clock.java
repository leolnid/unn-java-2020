package ru.leolnid.app.clock;

public interface Clock {
    int getSeconds();
    int getMinutes();
    int getHours();
    float getTime();

    void setTime(float time) throws ClockException;
    void addTime(float time) throws ClockException;
    void setPrice(float price) throws ClockException;
    float getPrice();
    String getMark();

    ClockType getType();

    void replace(Clock newClock);
}
