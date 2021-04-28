package me.antonhorvath.objects;

import java.util.ArrayList;
import java.util.Stack;

public class Flight {

    private final City currentCity;

    private final City destinationCity;

    private final boolean cost;

    private final ArrayList<Path> paths;

    public Flight(City currentCity, City destinationCity, boolean cost) {
        this.currentCity = currentCity;
        this.destinationCity = destinationCity;
        this.cost = cost;
        paths = new ArrayList<>();
        processPaths();
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public boolean isCost() {
        return cost;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void print() {
        System.out.println("Flight Info:");
        System.out.println("Current City: " + currentCity.getName());
        System.out.println("Destination City: " + destinationCity.getName());
        System.out.println("Method: " + (cost ? "Cost" : "Time\n"));
    }

    // backtracking algorithm
    private void processPaths() {
        Stack<Destination> stack = new Stack<>(); // implement a stack to keep track of which cities we are on
        Destination destination = currentCity.getDestination(); // we start at the first destination for the current city
        stack.push(destination);
        Destination traverse = destination;
        while (traverse != null) {
            if (traverse.getCity().equals(destinationCity) || traverse.getCity().getDestination() == null || stack.search(traverse.getCity().getDestination()) != -1) {
                if (traverse.getCity().equals(destinationCity)) {
                    paths.add(new Path(currentCity, stack.toArray()));
                }
                traverse = traverse.getDestination();
                if (!stack.empty()) {
                    stack.pop();
                }
                while (traverse == null && !stack.empty()) {
                    traverse = stack.pop().getDestination();
                }
                if (!stack.empty()) {
                    stack.push(traverse);
                }
            }
            else {
                stack.push(traverse.getCity().getDestination());
                traverse = traverse.getCity().getDestination();
            }
        }
    }

}
