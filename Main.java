import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static AirportCompanyManagement flightAirPort = AirportCompanyManagement.getInstance();
    public static Scanner scan = new Scanner(System.in);
    public static String choice;

    public static void main(String[] args) {
        initMinimalData();
        initDialog();
    }

    private static void initMinimalData() {
        System.out.println("**************************** Initializing Data ****************************");
        FlightCompany testCompany = new FlightCompany("ELAL");
        flightAirPort.addCollaborationCompany(testCompany);
        flightAirPort.sign_up("Employee", "Employee");
        testCompany.setRequestEmployeeUser("Employee");
        flightAirPort.sign_up("Customer", "Customer");
        flightAirPort.sign_out("Customer");
        flightAirPort.updateEmployees();

        EmployeeFlight user = (EmployeeFlight) flightAirPort.getUser("Employee");

        int[] date1 = {2025, 4, 18, 4, 15};
        int[] date2 = {2025, 4, 20, 6, 15};
        int[] date3 = {2024, 7, 19, 1, 15};
        int[] date4 = {2024, 5, 25, 2, 35};
        if (user != null) {
            flightAirPort.setNewFlight(user.createNewFlight("Eilat", "Greece", date1, 2, 500, 100));
            flightAirPort.setNewFlight(user.createNewFlight("Eilat", "Greece", date1, 2, 500, 100));
            flightAirPort.setNewFlight(user.createNewFlight("Eilat", "TLV", date2, 0.5, 300, 50));
            flightAirPort.setNewFlight(user.createNewFlight("Eilat", "TLV", date3, 0.5, 300, 50));
            flightAirPort.setNewFlight(user.createNewFlight("Eilat", "Greece", date4, 2, 500, 100));
            flightAirPort.sign_out("Employee");
        }

        System.out.println("***** Employee Username And Password: 'Employee' *****");
        System.out.println("***** Customer Username And Password: 'Customer' *****");
        System.out.println("***** Or Sign up and use Company Options for define user as Employee *****");
        System.out.println("**************************************************************************\n");

    }

    private static void initDialog() {
        while (true) {
            clearConsole();
            System.out.println(" **** Home Screen **** ");
            System.out.println("1. Log in");
            System.out.println("2. Log out");
            System.out.println("3. Sign up");
            System.out.println("4. Company Options");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scan.nextLine();
            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    logout();
                    break;
                case "3":
                    signup();
                    break;
                case "4":
                    companyDialog();
                    break;
                case "5":
                    return;
                default:
                    clearConsole();
            }
        }
    }

    private static void customerDialog(User user) {

        while (true) {
            clearConsole();
            System.out.println("\n**** Airport Actions Screen ****");
            System.out.println("1. Flight Options");
            System.out.println("2. Search for Company");
            System.out.println("3. My Flight Tickets");
            System.out.println("4. Check Balance");
            System.out.println("5. Insert Money");
            System.out.println("6. Check Notifications");
            System.out.println("7. Log out");
            System.out.print("Enter your choice: ");

            choice = scan.nextLine();

            switch (choice) {
                case "1":
                    showFlightsActions(user);
                    break;

                case "2":
                    showCompanyRedundantActions(user);
                    break;

                case "3":
                    clearConsole();
                    System.out.println("\n**** My Flight Tickets ****\n");
                    ((Customer)user).printMyTickets();
                    break;

                case "4":
                    clearConsole();
                    System.out.println("\n**** Check Balance ****");
                    System.out.println("Your balance is: " + ((Customer)user).getMyMoney());
                    break;

                case "5":
                    clearConsole();
                    System.out.println("\n**** Insert Money ****");
                    System.out.println("**** I Wish it was that easy, Only for display adding by enter a number ****");
                    System.out.print("Enter amount: ");
                    int amount;
                    try {
                        amount = scan.nextInt();
                    } catch (NumberFormatException nfe) {
                        amount = 0;
                    }
                    ((Customer)user).setMyMoney(amount);
                    scan.nextLine();
                    break;

                case "6":
                    clearConsole();
                    System.out.println("\n**** Notifications ****\n");
                    (user).showNotifications();
                    break;

                case "7":
                    logout();
                    return;

                default:
                    break;
            }
        }
    }

    private static void companyDialog() {
        Company company;
        while (true) {
            System.out.println("\n** Return by typing: Back **");
            System.out.print("Enter Company ID: ");
            String accessID = scan.nextLine();
            company = flightAirPort.getCompanyByID(accessID);
            if (company != null) {
                break;
            } else if (accessID.equals("Back")) {
                return;
            }
        }
        while (true) {
            clearConsole();
            System.out.println("\n**** Company Actions Screen ****");
            System.out.println("1. Add Company Collaboration With Airport");
            System.out.println("2. Remove Company Collaboration With Airport");
            System.out.println("3. Add Sub Company to Parent Company");
            System.out.println("4. Remove Sub Company to Parent Company");
            System.out.println("5. Request Employee User");
            System.out.println("6. Update Employees Users (After Request)");
            System.out.println("7. Company Notification Dialog");
            System.out.println("*** Return Back (enter any char) ***");
            System.out.print("Enter your choice: ");

            choice = scan.nextLine();
            switch (choice) {
                case "1":
                    createNewCompanyStartCollaborationWithAirport(company);
                    break;
                case "2":
                    removeCompanyCollaboration(company);
                    break;
                case "3":
                    addSubCompanyToCompany(company);
                    break;
                case "4":
                    removeSubCompanyFromCompany(company);
                    break;
                case "5":
                    RequestForUserEmployeeCreation(company);
                    break;
                case "6":
                    System.out.println("\nEmployees Requests Updated!");
                    flightAirPort.updateEmployees();
                    break;
                case "7":
                    companyNotificationDialog(company);
                    break;
                default:
                    return;
            }
        }
    }

    private static void companyNotificationDialog(Company company) {
        System.out.println("\n**** Company News Notification Dialog ****");
        System.out.println("1. General Notification");
        System.out.println("2. Discount Notification");
        System.out.println("*** Return Back (enter any char) ***");
        System.out.print("Enter your choice: ");

        String message;
        choice = scan.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter Message: ");
                message = scan.nextLine();
                company.notify(message);
                return;
            case "2":
                System.out.print("NOTE! Currently Available only for Flights Company\n");
                System.out.print("Enter 'All' for every flight of the company Or Enter Flight ID: ");
                String answer = scan.nextLine();
                System.out.print("Enter Discount Percentage: ");

                int discount;
                try {
                    discount = scan.nextInt();
                } catch (InputMismatchException ime) {
                    System.out.println("Invalid input type entered. Please enter numeric values.");
                    scan.nextLine(); // consume the invalid input
                    return;
                }

                if (answer.equals("All")) {
                    ((FlightCompany) company).flightDiscount(discount);
                } else {
                    Flight flight = flightAirPort.getFlightByID(answer);
                    if (flight != null) {
                        flight.setDiscount(discount);
                    } else {
                        System.out.println("Flight ID not found.");
                        return;
                    }
                }

                scan.nextLine();
                System.out.print("Enter Message: ");
                message = scan.nextLine();
                company.notify(message);
                return;

            default:
                return;
        }
    }


    private static void removeSubCompanyFromCompany(Company company) {
        clearConsole();
        System.out.println("\n**** Remove Sub Company ****");
        System.out.print("Enter Sub Company ID: ");
        String subCompanyID = scan.nextLine();
        Company subCompany = flightAirPort.getCompanyByID(subCompanyID);
        company.removeAllOccurrenceChildCompany(subCompany);
    }

    private static void RequestForUserEmployeeCreation(Company company) {
        System.out.println("\n**** Request Employee User ****");
        System.out.println("\n**** Note! If there is No User with that Username, employee update Wont Approve the Request!****");
        System.out.print("Enter Username: ");
        company.setRequestEmployeeUser(scan.nextLine());
    }

    private static void addSubCompanyToCompany(Company company) {
        clearConsole();
        flightAirPort.showCompanies(null);
        System.out.println("\n**** Add Sub Company ****");
        System.out.println("\n**** Note! You can make company sub of another only if the company exist in the Airport-Management ****");
        System.out.print("Enter Parent Company ID: ");
        String parentCompanyID = scan.nextLine();
        Company parentCompany = flightAirPort.getCompanyByID(parentCompanyID);
        System.out.print("Enter Sub Company ID: ");
        Company subCompany = flightAirPort.getCompanyByID(scan.nextLine());
        String subCompanyName = scan.nextLine();
        flightAirPort.addSubCompany(parentCompany, subCompany);
    }

    private static void removeCompanyCollaboration(Company company) {
        clearConsole();
        System.out.println("\n**** Remove Company ****");
        System.out.print("Enter Company ID: ");
        String companyID = scan.nextLine();
        flightAirPort.removeCompany(companyID);
    }

    private static void createNewCompanyStartCollaborationWithAirport(Company company) {
        clearConsole();
        System.out.println("\n**** Add Company ****");
        System.out.println("\nNOTE! only flight company available to be added for now.");
        System.out.print("Enter Company Name: ");
        String companyName = scan.nextLine();
        FlightCompany tempFlight = (FlightCompany) flightAirPort.getCompanyByName(companyName);
        if(tempFlight == null){
            tempFlight = new FlightCompany(companyName);
            flightAirPort.addCollaborationCompany(tempFlight);
        }else{
            System.out.println("****** Company already exist in the Airport-Management ******");
        }
    }

    private static void login() {
        clearConsole();
        System.out.println("\n**** Login ****");
        System.out.print("Enter Username: ");
        String username = scan.nextLine();
        System.out.print("Enter Password: ");
        String password = scan.nextLine();
        User userIs = flightAirPort.sign_in(username, password);
        if (userIs != null) customerDialog(userIs);
    }

    private static void logout() {
        clearConsole();
        System.out.println("\n**** Logout ****");
        System.out.println("Enter Username:");
        String username = scan.nextLine();
        clearConsole();
        flightAirPort.sign_out(username);
    }

    private static void signup() {
        clearConsole();
        System.out.println("\n**** Signup ****");
        System.out.print("Enter Username:");
        String username = scan.nextLine();
        System.out.print("Enter Password:");
        String password = scan.nextLine();
        User userRegistration = flightAirPort.sign_up(username, password);
        flightAirPort.updateEmployees();
        customerDialog(userRegistration);
    }

    private static void showFlightsActions(User user){
        boolean sortedFlag = false;

        while (true) {
            clearConsole();

            if(!sortedFlag){
                System.out.println("\n**** All Flights *****");
                flightAirPort.showFlights();
            }else sortedFlag = false;
            System.out.println("1. Sort Flights");
            System.out.println("2. Buy Ticket");
            boolean isEmployeeFlight = user instanceof EmployeeFlight;
            if(isEmployeeFlight){
                System.out.println("3. Create a New Flight");
                System.out.println("4. Change Flight Price");
                System.out.println("5. Change Flight Date And Time");
            }
            System.out.println("*** Return Back (enter any char) ***");
            System.out.print("Enter your choice: ");


            choice = scan.nextLine();

            switch (choice) {

                    case "1":
                        clearConsole();
                        sortedFlag = true;
                        sortFlights();
                        break;

                    case "2":
                        clearConsole();
                        buyTicket(user);
                        return;

                    case "3":
                        if (isEmployeeFlight) {
                            clearConsole();
                            employeeCreateNewFlight((EmployeeFlight) user);
                        }
                        return;

                    case "4":
                        if (isEmployeeFlight) {
                            clearConsole();
                            employeeChangeFlightPrice((EmployeeFlight) user);
                        }
                        return;

                    case "5":
                        if (isEmployeeFlight) {
                            clearConsole();
                            employeeChangeDateAndTime((EmployeeFlight) user);
                        }
                        return;

                    default:
                        return;
                }
        }
    }

    private static void sortFlights() {
        System.out.println("\n**** Sort Flights ****");
        System.out.println("1. Sort by Price");
        System.out.println("2. Sort by Date");
        System.out.println("3. Sort by Destination (Alphabetic)");
        System.out.print("Enter your choice: ");

        choice = scan.nextLine();
        switch (choice) {
                case "1":
                    flightAirPort.sortAndPrintByStrategy(new SortByPrice());
                    break;
                case "2":
                    flightAirPort.sortAndPrintByStrategy(new SortByDate());
                    break;
                case "3":
                    flightAirPort.sortAndPrintByStrategy(new SortByDestAlphabetic());
                    break;
                default:
                    return;
            }
    }

    private static void showCompanyRedundantActions(User user) {

        clearConsole();
        System.out.println("\n**** All Airport Companies ****");
        System.out.println("NOTE! Company you are following will be Signed by '(Following)'");
        flightAirPort.showCompanies(user);

        System.out.print("\nEnter your Company ID or Anything else to return: ");

        String chooseCompanyID = scan.nextLine();
        Company tempCompany = flightAirPort.getCompanyByID(chooseCompanyID);

        if (tempCompany == null) {
            return;
        }

        boolean isFlightEmployee = user instanceof EmployeeFlight;

        clearConsole();
        System.out.println("\n**** Company Actions Screen ****");
        System.out.println("1. Follow/Unfollow Company");
        if(isFlightEmployee) System.out.println("2. Notification Service");
        System.out.println("(Or any other key to return)");
        System.out.print("Enter your choice: ");
        String followingChoice = scan.nextLine();

        switch (followingChoice) {
            case "1":
                tempCompany.subscribe(user);
                return;
            case "2":
                if(isFlightEmployee){
                    companyNotificationDialog(tempCompany);
                }
                return;
            default:
                return;
        }
    }

    private static void buyTicket(User customer) {
        System.out.println("\n*** Buy a Ticket ***");
        flightAirPort.showFlights();
        System.out.print("Enter Flight ID: ");
        String flightID = scan.nextLine();
        Flight flight = flightAirPort.getFlightByID(flightID);

        if (flight != null && flight.getAvailableSeats() > 0) {
            ((Customer)customer).buyTicket(flight);
        } else {
            System.out.println("No available seats for this flight.");
        }
    }

    private static void employeeCreateNewFlight(EmployeeFlight employee) {

        System.out.println("\n*** Create New Flight Screen ***");
        System.out.print("Flight To: ");
        String to = scan.nextLine();
        System.out.print("Flight From: ");
        String from = scan.nextLine();

        boolean isForNewFlight = true;
        int[] dateTimeNumbers = getFromUserDateAndTime();
        double durationTime = 0;
        double ticketPrice = 0;
        int flightCapacity = 0;

        try {
            System.out.print("Duration Time: ");
            durationTime = Double.parseDouble(scan.nextLine()); // Convert to double
            System.out.print("Price per Ticket: ");
            ticketPrice = Double.parseDouble(scan.nextLine()); // Convert to double
            System.out.print("Capacity: ");
            flightCapacity = scan.nextInt(); // Read integer

        } catch (InputMismatchException ime) {
            System.out.println("Invalid input type entered. Please enter numeric values.");
            scan.nextLine(); // consume the invalid input
        } catch (NoSuchElementException nsee) {
            System.out.println("No input received.");
        }

        Flight flight = employee.createNewFlight(to, from, dateTimeNumbers, durationTime, ticketPrice, flightCapacity);
        flightAirPort.setNewFlight(flight);
    }

    private static void employeeChangeFlightPrice(EmployeeFlight employee){

        flightAirPort.showFlights();

        System.out.println("\n*** Change Flight Price ***");
        System.out.print("Enter Flight ID: ");
        String flightID = scan.nextLine();
        Flight tempFlight = flightAirPort.getFlightByID(flightID);

        if(tempFlight != null) {
            boolean isEmployeeOfFlightCompany = employee.flightActionVerfication(tempFlight.getCompany());
            if(isEmployeeOfFlightCompany){
                System.out.print("Enter the new Flight Price: ");
                double flightNewPrice = 0;
                try{
                    flightNewPrice = scan.nextInt();
                }catch (InputMismatchException ime) {
                    flightNewPrice = 0;
                }
                employee.changePrice(tempFlight, flightNewPrice);
            }
        }
    }

    private static void employeeChangeDateAndTime(EmployeeFlight employee){
        clearConsole();
        System.out.println("\n*** Change Flight Date And Time ***");
        flightAirPort.showFlights(); // Show all flights

        System.out.print("Enter Flight ID: "); // Prompt for flight ID
        String flightID = scan.nextLine();

        Flight tempFlight = flightAirPort.getFlightByID(flightID); // Retrieve the flight

        if(tempFlight != null) { // Check if the flight exists, if not, print an error message
            // Check if the employee is allowed to change flight data
            boolean isEmployeeAllowedChangeFlightData = employee.flightActionVerfication(tempFlight.getCompany());
            if(isEmployeeAllowedChangeFlightData) {
                int[] flightNewDateAndTime = getFromUserDateAndTime(); // Get the new date and time from the user

                if(flightNewDateAndTime != null) { // Check if the date and time are valid
                    employee.changeFlightDate(tempFlight, flightNewDateAndTime); // Change the flight date
                } else {
                    System.out.println("Invalid date and time format. Please try again.");
                }
            } else {
                System.out.println("You are not allowed to change the date and time of this flight.");
            }
        } else {
            System.out.println("Flight ID not found.");
        }
    }

    private static int[] getFromUserDateAndTime() {
        System.out.print("Enter date and time in the format YYYY MM DD HH mm (e.g., 2024 05 20 15 30): ");
        String dateTimeString = scan.nextLine(); // Reads the entire line
        String[] dateTimeParts = dateTimeString.split(" "); // Split the input string into parts
        if(dateTimeParts.length != 5) return null;

        int[] dateTimeNumbers = new int[5]; // To store year, month, day, hour, minute
        for (int i = 0; i < dateTimeNumbers.length; i++) {
            try {
                dateTimeNumbers[i] = Integer.parseInt(dateTimeParts[i]);
            }catch (NumberFormatException nfe){
                return null;
            }
        }
        return dateTimeNumbers;
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

