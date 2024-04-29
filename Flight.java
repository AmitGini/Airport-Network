import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Flight Using the Flyweight Design Pattern and Composite - flightData
public class Flight {

    private final FlightCompany company;
    private final String flightID;
    private final FlightNotifier notifyService;
    private final FlightData flightData;  // flyweight design pattern

    private List<Customer> flightCustomers;
    private ZonedDateTime dateTime;
    private double price;
    private int availableSeats;
    private double discountNumerical;

    public Flight(FlightCompany company,String flightID, String to, String from, ZonedDateTime dateTime, double duration, double price, int capacity){
        this.flightCustomers = new ArrayList<>();
        this.company = company;
        this.flightID = flightID;
        this.flightData = FlightDataFactory.getFlightData(to, from, capacity ,duration);
        this.dateTime = dateTime;
        this.price = price;
        this.availableSeats = capacity;
        this.notifyService = new FlightNotifier();
        this.discountNumerical = 0;
    }

    public void setPrice(double price) {
        if(price < 1){
            System.out.println("Invalid price");
        }else{
            this.price = price;
        }
    }

    public void setDateTime(ZonedDateTime dateTime){
        this.dateTime = dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm 'UTC' XXX '(IL)'");
        String formattedFutureDateTime = this.dateTime.format(formatter);
        String flightInfo = "Flight ID: " + this.flightID + " to: " + this.flightData.getTo() + " from: " + this.flightData.getFrom();
        this.notifyService.notifyDateFlightChange(flightInfo,formattedFutureDateTime);
    }

    public void setDecreaseAvailableSeats(Customer customer){
        if(customer == null) return;
        this.flightCustomers.add(customer);
        this.notifyService.addSubscriber(customer);
        availableSeats--;
    }

    /* Note there is no need to change the original price,
        since the getTicketPrice() method will return the
        price with the discount. */
    public void setDiscount(double percentage){
        this.discountNumerical = this.price * (percentage/100);
    }

    public ZonedDateTime getDateTime(){
        return this.dateTime;
    }

    public double getTicketPrice() {
        return this.price - this.discountNumerical;
    }

    public int getAvailableSeats() {
        return this.availableSeats;
    }

    public FlightCompany getCompany(){
        return this.company;
    }

    public String getFlightID(){
        return this.flightID;
    }

    public String getTo() {
        return this.flightData.getTo();
    }

    public String getFrom() {
        return this.flightData.getFrom();
    }

    public int getCapacity() {
        return this.flightData.getCapacity();
    }

    public String toString(){
        // define new Formatting the ZonedDateTime object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm 'UTC' XXX '(IL)'");
        String formattedDateTime = this.dateTime.format(formatter);
        return this.flightID + " - " + this.flightData.getFrom() + " - " + this.flightData.getTo() + " - " + formattedDateTime + " - " + this.flightData.getDurationTime() + " - " + getTicketPrice() + " - " + flightCustomers.size()+"/"+this.flightData.getCapacity();
    }

}
