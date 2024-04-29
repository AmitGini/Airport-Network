import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// using Composite Design Pattern and inheritance
public class Customer extends User {

    protected double myMoney;
    protected List<Flight> myFlightTickets;

    public Customer(String username, String password){
        super(username, password);
        this.myMoney = 0;
        this.myFlightTickets = new ArrayList<>();
    }

    public double getMyMoney() {
        return this.myMoney;
    }

    public boolean setMyMoney(double money) {
        if (money < 0) {
            if (this.myMoney - money < 0) {
                System.out.println("Insufficient amount of money");
                return false;
            }
        }
        this.myMoney += money;
        return true;
    }

    public boolean buyTicket(Flight flight){
        if(this.myMoney - flight.getTicketPrice() < 0){
            System.out.println("Insufficient amount of money");
            return false;
        }else{
            this.myMoney -= flight.getTicketPrice();
            this.myFlightTickets.add(flight);
            flight.setDecreaseAvailableSeats(this);
            System.out.println("Ticket purchased successfully!");
            return true;
        }
    }

    public void printMyTickets(){
        for (Flight flight : myFlightTickets) {
            System.out.println(flight);
        }
    }
}
