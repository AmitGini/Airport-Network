// Purpose: Flight Company Notifies all observers with a new message.
// Observer design pattern
public class CompanyNotifier extends NotificationService{
    public void notifyCompanySubscribers(String flightCompanyName, String info ){
        String message = flightCompanyName + " Flight Company: " + info;
        this.updateAll(message);
    }
}
