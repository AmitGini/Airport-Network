import java.time.ZonedDateTime;
import java.time.ZoneId;

public class EmployeeFlight extends Employee {

    private final FlightCompany myFlightCompany;

    public EmployeeFlight(FlightCompany flightCompany, String username, String password){
        super(flightCompany,username, password);
        this.myFlightCompany = flightCompany;
    }

    // Company can create flights, using this method and given the proper details.
    public Flight createNewFlight(String to, String from, int[] dateTime, double durationTime, double price, int capacity){
        if(to.isEmpty() || from.isEmpty() || durationTime < 0 || price < 1 || capacity < 1) {
            System.out.println("Flight detail are wrong, flight creation failed");
            return null;
        }

        ZonedDateTime flightDate = createFlightDateTime(dateTime);
        if(flightDate == null) return null;
        String flightID = this.myFlightCompany.getID() + this.myFlightCompany.getMyCompanyFlights().size();
        Flight flight = new Flight(this.myFlightCompany, flightID, to, from, flightDate, durationTime, price, capacity);
        this.myFlightCompany.addToFlightSet(flight);
        return flight;
    }

    public void changeFlightDate(Flight flight, int[] dateTime){
        if(this.flightActionVerfication(flight.getCompany())) {
            ZonedDateTime flightDate = createFlightDateTime(dateTime);
            if(flightDate != null ){
                flight.setDateTime(flightDate);
            }
        }
    }

    public void changePrice(Flight flight, double price) {
        if(this.flightActionVerfication(flight.getCompany())){
            if(price > 0){
                flight.setPrice(price);
            }else System.out.println("Invalid price, price should be greater than 0, There is no such things free gifts!");
        }
    }

    private ZonedDateTime createFlightDateTime(int[] dateTime){
        if (dateTime.length != 5){
            System.out.println("date detail are wrong, flight creation failed");
            return null;
        }
        ZonedDateTime currTime = ZonedDateTime.now(ZoneId.of("Asia/Jerusalem")); // Current Time
        ZonedDateTime flightDate = ZonedDateTime.of(dateTime[0], dateTime[1], dateTime[2], dateTime[3],dateTime[4],0,0,ZoneId.of("Asia/Jerusalem"));
        if(flightDate.isBefore(currTime)){
            System.out.println("Date to a flight cannot be before the current time, flight creation failed");
            return null;
        }

        return flightDate;
    }
}
