package ru.leolnid.app.clock;

public interface Clock {
    float getSeconds();
    float getMinutes();
    float getHours();

    void setTime(float time) throws ClockException;
    void addTime(float time) throws ClockException;
    void setPrice(float price) throws ClockException;
}
