import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static HashMap<String, ArrayList<Route>> route;
    private static HashMap<String, ArrayList<String>> airports;

    static {
        try {
            route = Route.routes();
            airports = Airport.readAirports();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<String> airportsInDest = new ArrayList<>();
    private static ArrayList<String> airportsInStart = new ArrayList<>();
    private static String startAirport;
    private static String destinationAirport;

    /**
     * It takes a state as a parameter and returns an ArrayList of all the possible successor states
     *
     * @param state the current state of the problem
     * @return The successor states of the current state.
     */
    public static ArrayList<Route> getSuccessorStates(String state){
        return route.get(state);
    }

    /**
     * This function takes in a state and returns true if the state is in the destination airport's state
     *
     * @param state the state that the user is currently in
     * @return A boolean value.
     */
    public static boolean foundDestination(String state){
        airportsInDest = airports.get(destinationAirport);
        return airportsInDest.contains(state);
    }


    /**
     * The function takes in a state and returns true if there is a path from the state to the destination, and false
     * otherwise
     *
     * @param startCountryCity the current state of the agent
     * @return The method returns a boolean value.
     */


    public static void bfs(String startCountryCity) {
        ArrayList<String> availableAirports = airports.get(startCountryCity);
        Queue<Node> frontier = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        for (String state : availableAirports) {
            Node start = new Node(state);
            if (foundDestination(start.getState())) start.solutionPath();
            frontier.add(start);
        }

        while (!frontier.isEmpty()) {
            Node currentAirport = frontier.remove();
            visited.add(currentAirport.getState());
            if (!route.containsKey(currentAirport.getState())) continue;
            ArrayList<Route> succStates = getSuccessorStates(currentAirport.getState());
            for (Route succState : succStates) {
                String destinationAirportCode = succState.getDestAirportCode();
                String airlineCode = succState.getAirlineCode();
                int nStops = succState.getStops();
                Node child = new Node(destinationAirportCode, airlineCode, nStops + currentAirport.nStops, currentAirport);
                if (!visited.contains(child.getState()) && !frontier.contains(child)) {
                    if (foundDestination(child.getState())) child.solutionPath();
                    frontier.add(child);
                }
            }
        }
    }

/**
 * It reads the start and destination airports from the file, and then calls the bfs method with the startAirport as the
 * parameter
 */
public static void main(String[] args) {
    try {
        File file = new File("accra-london.txt");
        Scanner scan = new Scanner(file);
        startAirport = scan.nextLine();
        destinationAirport = scan.nextLine();
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }

    try {
        airportsInStart = airports.get(startAirport);
        airportsInDest = airports.get(destinationAirport);
    } catch (Exception e) {
        System.out.println(e.getStackTrace());
    }

    // Calling the bfs method with the startAirport as the parameter.
    bfs(startAirport);
}



}



