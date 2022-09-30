import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Airport {
    /**
     * If the string is null or empty, return true. Otherwise, loop through the string and return false if any character is
     * not whitespace
     *
     * @param s The string to check.
     * @return A boolean value.
     */
    public static boolean isBlank(String s) {
        int stringLength;
        if (s == null || (stringLength = s.length()) == 0) return true;

        for (int i = 0; i < stringLength; i++) {
            if ((Character.isWhitespace(s.charAt(i))) == false) return false;
        }
        return true;
    }

    /**
     * The function reads the airport file and creates a hashmap with the city and country as the key and the IATA and ICAO
     * codes as the values
     *
     * @return A HashMap of the airport locations and their IATA and ICAO codes.
     */
    public static HashMap<String, ArrayList<String>> readAirports() throws FileNotFoundException {
        File airportfile = new File("C:\\Users\\USER\\OneDrive - Ashesi University\\airports.csv");
        Scanner scan = new Scanner(airportfile);
        HashMap<String, ArrayList<String>> airportLocation = new HashMap<>();
        while (scan.hasNextLine()) {
            final String REGEX =",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
            String data = scan.nextLine();
            String[] dataArray = data.split(REGEX,-1);
            if (dataArray[4].equals("\\N")) continue;
            String airportCode = dataArray[4];
            String cityCountry = dataArray[2] + ',' + dataArray[3];
            airportLocation.putIfAbsent(cityCountry, new ArrayList<>());
            airportLocation.get(cityCountry).add(airportCode);

        }
        scan.close();
        return airportLocation;
    }

    /**
     * It takes a csv file of airport codes and their corresponding longitudes and latitudes and returns a hashmap of
     * airport codes and their corresponding longitudes and latitudes
     *
     * @return A HashMap of airport codes and their corresponding longitudes and latitudes.
     */
    public static HashMap<String, String> AirportLongLat() throws FileNotFoundException {
        File airportfile = new File("airports.csv");
        Scanner scan = new Scanner(airportfile);
        HashMap<String, String> airportLongtitudes = new HashMap<>();
        while (scan.hasNextLine()) {
            final  String REGEX = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
            String data = scan.nextLine();
            String[] dataArray = data.split(REGEX, -1);
            if (dataArray[4].equals("\\N")) continue;
            String airportCode = dataArray[4];
            String LatAndLong = dataArray[6] + ',' + dataArray[7];
            airportLongtitudes.put(airportCode, LatAndLong);
        }
        scan.close();
        return airportLongtitudes;
    }
}


