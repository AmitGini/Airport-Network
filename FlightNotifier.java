// Purpose: All users that bought tickets for a Flight will be Notified with a new message.
public class FlightNotifier extends NotificationService{
    public void notifyDateFlightChange(String flightInfo, String dateTime){
        String message = flightInfo + " Date and Time has changed to:" + dateTime;
        this.updateAll(message);
    }

}
