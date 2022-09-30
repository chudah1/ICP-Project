import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Airline {
    private String id;
    private String location;
    private String iataCode;

    public Airline(String id,String location, String iataCode) {
        this.id = id;
        this.location = location;
        this.iataCode = iataCode;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getIataCode() {
        return iataCode;
    }


    public static  HashMap<String, ArrayList<String>> readAirlines() throws FileNotFoundException {
        File airportfile = new File("airlines.csv");
        Scanner scan = new Scanner(airportfile);
        //{country:['Airlines]'}
        HashMap<String, ArrayList<String>> airportLocation = new HashMap<>();
        ArrayList<String> airlines = new ArrayList<>();
        String airlineIdIataCode = "";
        while (scan.hasNextLine()){
            String [] data = scan.nextLine().split(",");
            if (Airport.isBlank(data[3]) || data[3].equals("\\N")) airlineIdIataCode = data[0] + '/' + data[4];
            else airlineIdIataCode = data[0] + '/' + data[3];
            airlines.add(airlineIdIataCode);
            String country = data[6];
            airportLocation.putIfAbsent(country, new ArrayList<>());
            airportLocation.get(country).add(airlineIdIataCode);
        }
        scan.close();
        for (String key : airportLocation.keySet() ){
            System.out.println(key +':'+ airportLocation.get(key));
        }
        System.out.println(airportLocation.get("Ghana"));
        return airportLocation;
    }

    public static void main (String [] args) throws FileNotFoundException {
        Airline.readAirlines();
    }
}
