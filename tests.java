public class tests {
    public static void main(String[] args){

        AirportCompanyManagement flightAirPort = new AirportCompanyManagement();
        FlightCompany testCompany = new FlightCompany();
        FlightCompany test2Company = new FlightCompany();
        flightAirPort.addCompany(testCompany);
        flightAirPort.addCompany(test2Company);

        int date[] = {2024, 04, 18, 4, 15};
        Flight testFlight = testCompany.createNewFlight("Eilat", "TLV", date, 0.5, 300, 50);

        int date2[] = {2024, 04, 20, 6, 15};
        Flight test2Flight = testCompany.createNewFlight("Eilat", "Greece", date2, 2, 500, 100);

        int date3[] = {2024, 7, 19, 1, 15};
        Flight test3Flight = test2Company.createNewFlight("Eilat", "TLV", date, 0.5, 300, 50);

        int date4[] = {2024, 5, 25, 2, 35};
        Flight test4Flight = test2Company.createNewFlight("Eilat", "Greece", date2, 2, 500, 100);

        flightAirPort.showFlights();
//        List<Flight> testFlightSet = testCompany.getFlightList();
//        for(Flight f : testFlightSet){
//            System.out.println(f);
//        }


    }
}


/*
        // todo: Current time
        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Asia/Jerusalem"));

        // todo: modified time
        ZonedDateTime futureZonedDateTime = ZonedDateTime.of(2025, 1, 1, 15, 0, 0, 0, ZoneId.of("Asia/Jerusalem"));
        // todo: define new Formatting the ZonedDateTime object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm 'UTC' XXX '(IL)'");
        String formattedDateTime = dateTime.format(formatter); //todo: using time format method for curr time
        String formattedFutureDateTime = futureZonedDateTime.format(formatter);  //todo: using time format method for future time
        // Output the future and current date, time, and time zone todo: output
        System.out.println("Current Date, Time, and Time Zone: " + formattedDateTime);
        System.out.println("Future Date, Time, and Time Zone: " + formattedFutureDateTime);

        // Comparing with another date-time
        ZonedDateTime dateTimeEarlier = dateTime.minusDays(1);
        if (dateTime.compareTo(futureZonedDateTime) < 0) {
            System.out.println("Current dateTime is before Future dateTime.");
        }
 */