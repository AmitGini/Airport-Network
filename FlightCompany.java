import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class FlightCompany extends ConcreteCompany {

    private Set<Flight> myFlights;
    private Map<String, FlightEmployee> myEmployees; // String - employee username, Employee - object

    // Constructor
    public FlightCompany(String companyName){
        super(CompanyPrefix.FLIGHT_ID.getPrefix(), companyName); //FLC - Flight Company same prefix of companyID
        this.myFlights = new HashSet<>();
        this.myEmployees = new HashMap<>();
    }

    public FlightEmployee createEmployee(String username, String password){
        if(myEmployees.containsKey(username)) {
            System.out.println("username is already in use");
        }
        FlightEmployee em = new FlightEmployee(this, username, password);
        myEmployees.put(username,em);
        return em;
    }

    public Flight addFlight(String to, String from, ZonedDateTime flightDate,double durationTime, double price, int capacity){
        String flightID = (this.myFlights.isEmpty()) ? this.companyID + 0 : this.companyID + "-F" +this.myFlights.size();
        Flight flight = new Flight(this, flightID, to, from, flightDate, durationTime, price, capacity);
        myFlights.add(flight);
        return flight;
    }

    public Set<Flight> getFlightSet() {
        return this.myFlights;
    }


}
