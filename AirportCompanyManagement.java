import java.util.HashSet;
import java.util.Set;

public class AirportCompanyManagement {

    HashSet<Company> allCompanies;

    public AirportCompanyManagement(){
        this.allCompanies = new HashSet<>();
    }

    public void addCompany(FlightCompany company){
        this.allCompanies.add(company);
    }

    public void showFlights(){
        for(Company comp : this.allCompanies){
            if(comp.getCompanyID().contains(CompanyPrefix.FLIGHT_ID.getPrefix())){
                Set<Flight> flightSet = ((FlightCompany)comp).getFlightSet();
                for(Flight flight : flightSet){
                    System.out.println(flight);
                }
            }
        }
    }

}
