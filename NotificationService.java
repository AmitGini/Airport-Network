import java.util.HashSet;
import java.util.Set;

public abstract class NotificationService<T extends User> {

    private Set<T> subscribers;

    public NotificationService(){
        this.subscribers = new HashSet();
    }

    public void addSubscriber(T user){
        if(user != null){
            this.subscribers.add(user);
        }
    }

    public void removeSubscriber(T user){
        if(user!=null){
            this.subscribers.remove(user);
        }
    }

    public void updateAll(String message){
        for(User user: this.subscribers){
            user.update(message);
        }
    }

    public boolean isSubscriber(T user){
        for(T subscriber: this.subscribers){
            if(subscriber.getUsername().equals(user.getUsername())) return true;
        }
        return false;
    }

}
