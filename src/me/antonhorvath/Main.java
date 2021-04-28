package me.antonhorvath;

import me.antonhorvath.objects.Flight;
import me.antonhorvath.objects.Locations;
import me.antonhorvath.utility.FileManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static String delimiter = " ";

    public static void main(String[] args) {

        // Get all file information ----------------------------------------------------------------------------------------
        String files = FileManager.getFileNames();
        if (files.split(delimiter).length != 4) {
            System.out.println("Incorrect command input. Terminating program. Please follow the format in project guidelines.");
            return;
        }
        // Get all file information ----------------------------------------------------------------------------------------


        // Populate city *linked list* and destinations ----------------------------------------------------------------------------------------
        Locations locations = new Locations();
        FileManager.populateLocations(locations, files.split(delimiter)[1]);

        // debugger
        locations.printCities();
        // Populate city linked list and destinations ----------------------------------------------------------------------------------------


        // Obtain all flights needed to be evaluated ----------------------------------------------------------------------------------------
        ArrayList<Flight> flights = new ArrayList<>();
        FileManager.populateFlights(locations, flights, files.split(delimiter)[2]);

        // debugger
        for (Flight f : flights) {
            f.print();
        }
        // Obtain all flights needed to be evaluated ----------------------------------------------------------------------------------------


        // Output best paths to file ----------------------------------------------------------------------------------------
        FileManager.output(flights, files.split(delimiter)[3]);
        // Output best paths to file ----------------------------------------------------------------------------------------


    }
}
