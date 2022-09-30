import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Route {
    private String destAirportCode;
    private String airlineCode;
    private int stops;

    public Route(String destAirportCode, String airlineCode, int stops) {
        this.destAirportCode = destAirportCode;
        this.airlineCode = airlineCode;
        this.stops = stops;

    }


    /**
     * This function returns the destination airport code
     *
     * @return The destination airport code.
     */

    public String getDestAirportCode() {
        return destAirportCode;
    }

    /**
     * This function returns the airline code of the flight
     *
     * @return The airline code.
     */
    /**


     * This function returns the airline code of the flight
     *
     * @return The airline code.
     */
    public String getAirlineCode() {
        return airlineCode;
    }


    /**
     * This function returns the number of stops.
     *
     * @return The number of stops.
     */
    public int getStops() {
        return stops;
    }


    /**
     * It reads the routes.csv file and creates a HashMap with the source airport as the key and the value is an ArrayList
     * of objects of the Data class
     *
     * @return The method returns a HashMap with the key being the source airport code and the value being an ArrayList of
     * Data objects.
     */
    public static HashMap<String,ArrayList<Route>> routes() throws FileNotFoundException {
        File routesfile = new File("routes.csv");
        Scanner scan = new Scanner(routesfile);
        HashMap<String,ArrayList<Route>> airlineCountry = new HashMap<>();
        // Reading the file line by line.
        while (scan.hasNextLine()){
            String [] data = scan.nextLine().split(",");
            String sourceAirportCode = data[2];
            String destinationAirportCode = data[4];
            String airlineCode = data[0];
            int stops = Integer.parseInt(data[7]);
            Route node = new Route(destinationAirportCode, airlineCode,stops);

            // Checking if the key is present in the HashMap. If it is not present, it adds the key and the value to the
            // HashMap. If it is present, it adds the value to the key.
            airlineCountry.putIfAbsent(sourceAirportCode, new ArrayList<>());
            airlineCountry.get(sourceAirportCode).add(node);
        }
        scan.close();
    return airlineCountry;
    }

}
