import java.util.ArrayList;
import java.util.List;

public abstract class User extends ISubscriber{

    protected String myUsername;
    protected String myPassword;
    protected boolean connection;
    protected List<String> myNotifications;


    public User(String username, String password){
        this.myUsername = username;
        this.myPassword = password;
        this.connection = true;
        this.myNotifications = new ArrayList<>();
    }

    public String getUsername(){
        return this.myUsername;
    }

    public String getMyPassword() {return this.myPassword; }

    public boolean isConnected(){
        return this.connection;
    }

    public User connect(String password){
        if(this.myPassword.equals(password)){
            if(this.connection == true){
                System.out.println("**** Already Connected ****");
            }else {
                this.connection = true;
                System.out.println("**** " + this.myUsername + " Connected Successfully" + " ****");
            }
            return this;
        } return null;
    }

    public void disconnect(){
        if(this.connection == false){
            System.out.println("**** " + this.myUsername + " Already Disconnected" + " ****");
        }else {
            this.connection = false;
            System.out.println("**** " + this.myUsername + " Disconnected successfully" + " ****");
        }
    }

    @Override
    public void update(String message){
        this.myNotifications.add(message);
    }

    public void showNotifications() {
        System.out.println("**** Notifications for " + this.myUsername + " ****");
        for (String notification : myNotifications) {
            System.out.println(notification);
        }
    }
}
