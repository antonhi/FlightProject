package me.antonhorvath.objects;

import java.util.ArrayList;
import java.util.Comparator;

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
        this.totalTime = getTime();
        this.totalPrice = getCost();
    }

    public String getInfo() {
        String s = currentCity.getName();
        for (Destination destination : destinations) {
            s += (" -> " + destination.getCity().getName());
        }
        s += (". Time: " + (int)getTime() + " Cost: " + getCost());
        return s;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public boolean isCheaperThan(Path path) {
        if (path.totalPrice < this.totalPrice) {
            return false;
        }
        return true;
    }

    public boolean isFasterThan(Path path) {
        if (path.totalTime < this.totalTime) {
            return false;
        }
        return true;
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

class PathPrice implements Comparator<Path> {

    @Override
    public int compare(Path path1, Path path2) {
        if (path1.isCheaperThan(path2)) {
            return -1;
        }
        return 1;
    }
}

class PathTime implements Comparator<Path> {

    @Override
    public int compare(Path path1, Path path2) {
        if (path1.isFasterThan(path2)) {
            return -1;
        }
        return 1;
    }
}

