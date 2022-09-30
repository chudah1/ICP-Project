import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Node {
    private String state;
    private String airlineCode;
    private Node parent;
    int nStops;

    public Node(String state, String airlineCode, int nStops, Node parent) {
        this.state = state;
        this.parent = parent;
        this.airlineCode = airlineCode;
        this.nStops = nStops;
    }

    public Node(String state) {
        this.state = state;
        this.parent = null;
        this.airlineCode = "";
        this.nStops = 0;


    }


    /**
     * This function returns the state of the object
     *
     * @return The state of the object.
     */
    public String getState() {
        return state;
    }

    /**
     * This function returns the airline code of the flight
     *
     * @return The airline code.
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * Returns the parent of this node.
     *
     * @return The parent node of the current node.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * This function is used to check if the current node is equal to the node passed as a parameter
     *
     * @param obj The object to compare with.
     * @return The hashcode of the current node.
     */
    @Override
    // This function is used to check if the current node is equal to the node passed as a parameter.
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Node)) return false;
        Node node = (Node) obj;
        return this.state.equals(node.state);

    }

    /**
     * This function is used to print the solution path to a file
     */
    public void solutionPath() {
        String startCity ="";
        String destinationCity="";
        try {
            File file = new File("accra-london.txt");
            Scanner scan = new Scanner(file);
            startCity = scan.nextLine().split(",")[0];
            destinationCity = scan.nextLine().split(",")[0];
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> solution = new ArrayList<>();
        String path = "";
        Node curr = this;
        while (curr.parent != null) {
            path = curr.getAirlineCode() + " from " + curr.getParent().getState() + " to " + curr.getState() + " " + curr.nStops + " stops";
            solution.add(path);
            curr = curr.parent;
        }
        Collections.reverse(solution);

        try {
            FileWriter myWriter = new FileWriter(startCity + "-" + destinationCity +"_" + "output.txt");
            PrintWriter printWriter = new PrintWriter(myWriter);
            int number = 1;
            for (String solutionpath : solution) {
                printWriter.println(number+". " + solutionpath);
                number++;
            }
            printWriter.println("Total flights: " + "" + solution.size());
            printWriter.println("Total additional stops: " + "" + nStops);
            printWriter.println("Optimality Criteria: Flights");
            printWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
