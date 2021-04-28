package me.antonhorvath.objects;

public class City {

    private final String name;

    private City city;

    private Destination destination;

    public City(String name) {
        this.name = name;
        city = null;
        destination = null;
    }

    public boolean isCity(String name) {
        return name.equals(this.name);
    }

    public City getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void attachDestination(Destination destination) {
        Destination traverse = this.destination;
        if (traverse != null) {
            while (traverse.getDestination() != null) {
                traverse = traverse.getDestination();
            }
            traverse.setDestination(destination);
        }
        else {
            this.destination = destination;
        }
    }

    // debugger for destinations linked list
    public void printDestinations() {
        Destination traverse = this.destination;
        System.out.print("Destinations for " + getName() + ": ");
        while (traverse != null) {
            System.out.print(traverse.getCity().getName() + ", ");
            traverse = traverse.getDestination();
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof City) {
            return ((City) o).getName().equals(this.name);
        }
        return false;
    }


}
