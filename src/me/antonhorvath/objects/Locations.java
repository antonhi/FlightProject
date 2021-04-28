package me.antonhorvath.objects;

import me.antonhorvath.objects.City;

public class Locations {

    private City city;

    private int amount;

    public Locations() {
        city = null;
        amount = 0;
    }

    public void attachCity(City city) {
        City traverse = this.city;
        if (traverse != null) {
            while (traverse.getCity() != null) {
                traverse = traverse.getCity();
            }
            traverse.setCity(city);
        }
        else {
            this.city = city;
        }
        amount+=1;
    }

    public City getCity() {
        return city;
    }

    public int getAmount() {
        return amount;
    }

    public City hasCity(String city) {
        City traverse = this.city;
        while (traverse != null) {
            if (traverse.isCity(city)) {
                return traverse;
            }
            traverse = traverse.getCity();
        }
        return null;
    }

    // debugger to view linked list
    public void printCities() {
        City traverse = this.city;
        System.out.println("Cities in Locations: \n");
        while (traverse != null) {
            System.out.println(traverse.getName());
            traverse.printDestinations();
            traverse = traverse.getCity();
        }
        System.out.println();
    }


}
