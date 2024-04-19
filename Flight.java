import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class Flight {

    private FlightCompany company;
    private HashSet<String> passengersID;
    private String flightID;
    private String to;
    private String from;
    private ZonedDateTime dateTime;
    private double durationTime;
    private double price;
    private int capacity;

    public Flight(FlightCompany company,String flightID, String to, String from, ZonedDateTime dateTime, double duration, double price, int capacity){
        this.passengersID = new HashSet<>();
        this.company = company;
        this.flightID = flightID;
        this.to = to;
        this.from = from;
        this.dateTime = dateTime;
        this.durationTime = duration;
        this.price = price;
        this.capacity = capacity;
    }

    public String toString(){
        // define new Formatting the ZonedDateTime object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm 'UTC' XXX '(IL)'");
        String formattedFutureDateTime = this.dateTime.format(formatter);  //todo: using time format method for future time
        return this.flightID + " - " + this.from + " - " + this.to + " - " + formattedFutureDateTime + " - " + this.durationTime + " - " + price + " - " + passengersID.size()+"/"+capacity;
    }

}
