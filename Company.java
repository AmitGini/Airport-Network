import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface Company {

    public String getID();
    public String getName();
    public Set<Company> getChildrenCompany();
    public HashSet<String> getRequests();
    public void setRequestEmployeeUser(String username);
    public void setChildCompany(Company company);
    public void removeAllOccurrenceChildCompany(Company subCompany);
    public void updateRequests(HashMap<String,Employee> status);
    public boolean isChildOf(Company company);
    public boolean isSubscriber(User user);
    public void notify(String details);
    public void subscribe(User user);

}