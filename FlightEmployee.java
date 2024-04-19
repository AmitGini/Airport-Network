import java.time.ZonedDateTime;
import java.time.ZoneId;


public class FlightEmployee extends Employee {

    public FlightEmployee(FlightCompany flightCompany, String username, String password){
        super(flightCompany,username, password);
    }

    @Override
    public FlightCompany getMyCompany(){
        return (FlightCompany) this.myCompany;
    }

    // Company can create flights, using this method and given the proper details.
    public Flight createNewFlight(String to, String from, int[] dateTime, double durationTime, double price, int capacity){
        if (dateTime.length != 5 || to.isEmpty() || from.isEmpty() || durationTime < 0 || price < 1 || capacity < 1){
            System.out.println("Flight detail are wrong, flight creation failed");
            return null;
        }

        ZonedDateTime currTime = ZonedDateTime.now(ZoneId.of("Asia/Jerusalem")); // Current Time
        ZonedDateTime flightDate = ZonedDateTime.of(dateTime[0], dateTime[1], dateTime[2], dateTime[3],dateTime[4],0,0,ZoneId.of("Asia/Jerusalem"));
        if(flightDate.isBefore(currTime)){
            System.out.println("Date to a flight cannot be before the current time, flight creation failed");
            return null;
        }

        return this.getMyCompany().addFlight(to, from, flightDate, durationTime, price, capacity);
    }

    //todo: change flight date

    //todo: cancel flight


}
