import java.util.HashSet;
import java.util.Set;

// Observer design pattern
public abstract class NotificationService{

    private Set<User> subscribers;

    public NotificationService(){
        this.subscribers = new HashSet();
    }

    public void addSubscriber(User user){
        if(user != null){
            this.subscribers.add(user);
        }
    }

    public void removeSubscriber(User user){
        if(user!=null){
            this.subscribers.remove(user);
        }
    }

    public void updateAll(String message){
        for(User user: this.subscribers){
            user.update(message);
        }
    }

    public boolean isSubscriber(User user){
        for(User subscriber: this.subscribers){
            if(subscriber.getUsername().equals(user.getUsername())) return true;
        }
        return false;
    }

}
