package me.antonhorvath.objects;

public class Destination {

    private final City city;

    private final double time;

    private final double price;

    private Destination destination;

    public Destination(City city, double time, double price) {
        this.city = city;
        this.time = time;
        this.price = price;
        destination = null;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public double getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public Destination getDestination() {
        return destination;
    }

    public City getCity() {
        return city;
    }



}
