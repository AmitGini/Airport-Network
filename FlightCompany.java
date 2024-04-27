import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class FlightCompany extends ConcreteCompany {

    private HashSet<Flight> myFlights;

    // Constructor
    public FlightCompany(String companyName){
        super(PrefixCompanyID.FLIGHT_ID.getPrefix(), companyName); //FLC - Flight Company same prefix of companyID
        this.myFlights = new HashSet<>();
        super.myEmployee = new HashMap<String, EmployeeFlight>();
    }

    public void addToFlightSet(Flight flight){
        this.myFlights.add(flight);
    }

    public HashSet<Flight> getMyCompanyFlights() {
        return this.myFlights;
    }

    public void flightDiscount(double discountPercentage){
        if(discountPercentage < 0 || discountPercentage > 99){
            System.out.println("Invalid discount percentage");
        }else{
            for(Flight flight : this.myFlights){
                flight.setDiscount(discountPercentage);
            }
        }
    }

}
