package me.antonhorvath.objects;

public class Locations {

    private City city;

    public Locations() {
        city = null;
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
