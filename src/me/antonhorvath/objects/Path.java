package me.antonhorvath.objects;

import java.util.ArrayList;

public class Path {

    private double totalPrice;

    private double totalTime;

    private City currentCity;

    private ArrayList<Destination> destinations = new ArrayList<>();

    public Path(City currentCity, Object [] destinations) {
        this.currentCity = currentCity;
        for (Object o : destinations) {
            if (o instanceof Destination) {
                this.destinations.add((Destination) o);
            }
        }
    }

    public String getInfo() {
        String s = currentCity.getName();
        for (Destination destination : destinations) {
            s += (" -> " + destination.getCity().getName());
        }
        s += (". Time: " + (int)getTime() + " Cost: " + getCost());
        return s;
    }

    private double getCost() {
        double total = 0;
        for (Destination destination : destinations) {
            total += destination.getPrice();
        }
        return total;
    }

    private double getTime() {
        double total = 0;
        for (Destination destination : destinations) {
            total += destination.getTime();
        }
        return total;
    }



}
