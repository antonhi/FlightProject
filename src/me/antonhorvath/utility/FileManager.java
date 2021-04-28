package me.antonhorvath.utility;

import me.antonhorvath.objects.City;
import me.antonhorvath.objects.Destination;
import me.antonhorvath.objects.Flight;
import me.antonhorvath.objects.Locations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileManager {

    public static void populateLocations(Locations locations, String flightDataFile) {
        File flightData;
        Scanner scan;
        int iteration = 0;
        try {
            flightData = new File(flightDataFile);
            scan = new Scanner(flightData);
        } catch(FileNotFoundException e) {
            System.out.println("Error: Couldn't find flight data file");
            e.printStackTrace();
            return;
        }
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (iteration != 0) {
                if (line.split(Pattern.quote("|")).length == 4) {
                    String [] data = line.split(Pattern.quote("|"));

                    // add cities to the locations linked list if not present ---------------------------------------
                    if (locations.hasCity(data[0]) == null) {
                        locations.attachCity(new City(data[0]));
                    }
                    if (locations.hasCity(data[1]) == null) {
                        locations.attachCity(new City(data[1]));
                    }
                    // add cities to the locations linked list if not present ---------------------------------------


                    // add destination to both cities ---------------------------------------
                    City city1 = locations.hasCity(data[0]);
                    City city2 = locations.hasCity(data[1]);
                    double price = getPrice(data[2], iteration);
                    double time = getTime(data[3], iteration);

                    city1.attachDestination(new Destination(city2, time, price));
                    city2.attachDestination(new Destination(city1, time, price));
                    // add destination to both cities ---------------------------------------

                }
                else {
                    System.out.println("Error on line " + (iteration+1) + " of flight data file. Does not follow format!");
                    System.out.println("  > Not considering line due to improper format");
                }
            }
            iteration++;
        }
    }

    public static void populateFlights(Locations locations, ArrayList<Flight> flights, String flightsFile) {
        File requestedFlights;
        Scanner scan;
        int iteration = 0;
        try {
            requestedFlights = new File(flightsFile);
            scan = new Scanner(requestedFlights);
        } catch(FileNotFoundException e) {
            System.out.println("Error: Couldn't find requested flight data file");
            e.printStackTrace();
            return;
        }

        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (iteration != 0) {
                if (line.split(Pattern.quote("|")).length == 3) {
                    String [] data = line.split(Pattern.quote("|"));
                    City current = locations.hasCity(data[0]);
                    City destination = locations.hasCity(data[1]);
                    if (current != null && destination != null) {
                        Flight f = new Flight(current, destination, data[2].equals("C"));
                        flights.add(f);
                    }
                    else {
                        System.out.println("Error on line " + (iteration+1) + " of requested flight data file. A city is invalid!");
                        System.out.println("  > Not adding flight");
                    }

                }
                else {
                    System.out.println("Error on line " + (iteration+1) + " of requested flight data file. Does not follow format!");
                    System.out.println("  > Not considering line due to improper format");
                }
            }
            iteration+=1;
        }

    }

    public static void output(ArrayList<Flight> flights, String outputFile) {
        File output = new File(outputFile);
        if (!output.exists()) {
            try {
                output.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: Couldn't generate output file with the given name");
                return;
            }
        }

        FileWriter writer;
        try {
            writer = new FileWriter(output);
        } catch (IOException e) {
            System.out.println("Error: Output file could not be found, cannot write to file");
            return;
        }

        int number = 1;
        for (Flight flight : flights) {
            try {
                writer.write("Flight " + number + ": " + flight.getCurrentCity().getName() + ", " +
                        flight.getDestinationCity().getName() + " (" + (flight.isCost() ? "Cost" : "Time") + ")\n");
                int paths = flight.getPaths().size() < 3 ? flight.getPaths().size() : 3;
                for (int i = 0; i < paths; i++) {
                    writer.write("Path " + (i+1) + ": " + flight.getPaths().get(i).getInfo() + "\n");
                }
                writer.write("\n");
            } catch(IOException e) {
                System.out.println("Error: Could not write to file!");
                return;
            }
            number+=1;
        }

        try {
            writer.close();
        } catch(IOException e) {
            System.out.println("Error: Could not close file writer");
        }
    }

    public static String getFileNames() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter file names in format: flightPlan <flightdatafile> <pathstocalculatefile> <outputfile>:");
        return scan.nextLine();
    }

    private static double getPrice(String price, int line) {
        double p = 0;
        try {
            p = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            System.out.println("Error with price for line " + (line+1) + ". Setting to 0");
        }
        return p;
    }

    private static double getTime(String time, int line) {
        double t = 0;
        try {
            t = Double.parseDouble(time);
        } catch (NumberFormatException e) {
            System.out.println("Error with time for line " + (line+1) + ". Setting to 0");
        }
        return t;
    }

}
