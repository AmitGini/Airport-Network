import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// using Composite and Strategy Design Pattern
public class AirportCompanyManagement {

    private static AirportCompanyManagement instance = null;

    private HashSet<Company> companies;
    private HashMap<String, User> users;
    private List<Flight> flights;
    private SortStrategy sortStrategy;

    private AirportCompanyManagement(){
        this.companies = new HashSet<>();
        this.users = new HashMap<>();
        this.flights = new ArrayList<>();
    }

    public static AirportCompanyManagement getInstance(){
        if(instance == null){
            instance = new AirportCompanyManagement();
        }
        return instance;
    }

    public Company getCompanyByID(String id){
        for(Company comp : this.companies){
            if(comp.getID().equals(id)) return comp;
        }
        return null;
    }

    public Company getCompanyByName(String name){
        for(Company comp : this.companies){
            if(comp.getName().equals(name)) return comp;
        }
        return null;
    }

    public Flight getFlightByID(String id){
        for(Flight flight : this.flights){
            if(flight.getFlightID().equals(id)) return flight;
        }
        return null;
    }

    public void sortAndPrintByStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
        sortStrategy.sort(this.flights);
        System.out.println("\n*************** Sorted Flights *****************");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
        System.out.println("************************************************\n");
    }

    public void setNewFlight(Flight newFlight){
        if(newFlight != null){
            flights.add(newFlight);
        }
    }

    public User sign_up(String username, String password){ // creating new users
        if(this.users.containsKey(username)){
            System.out.println("Username is already in use!");
            return null;

        }else if(username.isEmpty() || password.isEmpty()) {
            System.out.println("Invalid arguments!");
            return null;
        }

        User user = new Customer(username,password);
        this.users.put(username,user);
        return user;
    }

    public User sign_in(String username, String password){
        User user = null;
        if(this.users.containsKey(username)){
            user = this.users.get(username).connect(password);
        }
        return user;
    }

    public void sign_out(String username){
        if(this.users.containsKey(username)){
            this.users.get(username).disconnect();
        }
    }

    public User getUser(String username){
        return this.users.get(username);
    }

    public void showFlights() {
        System.out.println("******************************************************");
        for (Company comp : this.companies) {
            if(comp instanceof FlightCompany){
                for(Flight flight: ((FlightCompany) comp).getMyCompanyFlights()){
                    System.out.println(flight);
                }
            }
        }
        System.out.println("******************************************************");
    }

    public void showCompanies(User user){
        System.out.println("******************************************************");
        String isFollow;
        for (Company comp : this.companies) {
            isFollow = "";
            if(comp.isSubscriber(user)) isFollow = " (Following) ";
            System.out.println(isFollow + "Company name:" + comp.getName() + "  Company ID: " + comp.getID());
        }
        System.out.println("******************************************************");
    }

    public void addCollaborationCompany(Company company) {
        this.companies.add(company);
        if(company instanceof FlightCompany) {
            flights.addAll(((FlightCompany) company).getMyCompanyFlights());
        }
    }

    public void updateEmployees(){
        HashSet<String> employeeRequests;
        HashMap<String,Employee> finalRequestStatus;

        for(Company company : this.companies){ // loop over all companies
            employeeRequests = company.getRequests();
            finalRequestStatus = new HashMap<>();

            for(String username: employeeRequests){ // loop over all there requests for user employee creations

                if(this.users.containsKey(username)){
                    String pass = this.users.get(username).getMyPassword();

                    if(company instanceof FlightCompany) {
                        EmployeeFlight empFlight = new EmployeeFlight((FlightCompany) company, username, pass);
                        this.users.put(username,empFlight);
                        finalRequestStatus.put(username, empFlight);
                    }
                }
            }
            company.updateRequests(finalRequestStatus);
        }
    }

    public void removeCompany(String companyID) {
        Company company = getCompanyByID(companyID);
        if(company != null){
            this.companies.remove(company);
            if(company instanceof FlightCompany){
                for(Flight flight: ((FlightCompany) company).getMyCompanyFlights()){
                    this.flights.remove(flight);
                }
            }
        }
    }

    public void addSubCompany(Company comp, Company childComp){
        comp.setChildCompany(childComp);
    }



}


