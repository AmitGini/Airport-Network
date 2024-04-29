import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// using Composite Design Pattern and implementations
public class ConcreteCompany implements Company{

    private static char KeyID = 'A';

    protected final String companyID;
    protected final String companyName;
    protected HashSet<Company> subCompanies;
    protected HashMap<String, Employee> myEmployee; // employee flight inheritance and flexibility
    protected CompanyNotifier notifyService;
    protected HashSet<String> employeeRequests;

    public ConcreteCompany(String companyID, String companyName){
        this.companyID = companyID + KeyID;
        this.companyName = companyName;
        this.employeeRequests = new HashSet<>();
        this.subCompanies = new HashSet<>();
        this.myEmployee = new HashMap<>();
        this.notifyService = new CompanyNotifier();
        KeyID++;
    }

    @Override
    public void setChildCompany(Company company) {
        if(this.isChildOf(company)){
           System.out.println("Cannot add a parent company as a sub company");
        }else {
            subCompanies.add(company);
        }
    }

    @Override
    public void removeAllOccurrenceChildCompany(Company subCompany) {
        dfsRemove(this, subCompany);
    }

    @Override
    public String getID(){
        return this.companyID;
    }

    @Override
    public String getName(){
        return this.companyName;
    }

    @Override
    public Set<Company> getChildrenCompany() {
        return this.subCompanies;
    }

    @Override
    public void setRequestEmployeeUser(String username) {
        if(username.isEmpty()){
            System.out.println("Invalid username");
        }
        else if(this.employeeRequests.contains(username)){
            System.out.println("That username has already been requested to be created as employee of" + this.companyName);
        }
        else this.employeeRequests.add(username);
    }

    @Override
    public HashSet<String> getRequests(){
        return this.employeeRequests;
    }

    @Override
    public boolean isChildOf(Company company) {
        if(company.getID().equals(this.companyID)) return true;
        if(company.getChildrenCompany().isEmpty()) return false;

        boolean isSub = false;
        for(Company comp : company.getChildrenCompany()){
            isSub |= this.isChildOf(comp);
        }
        return isSub;
    }

    @Override
    public void updateRequests(HashMap<String, Employee> status) {
        Iterator<String> iterator = this.employeeRequests.iterator();
        while (iterator.hasNext()) {
            String username = iterator.next();
            if (status.containsKey(username)) {
                this.myEmployee.put(username, (Employee) status.get(username));
                iterator.remove();  // Removing using the iterator
            }
        }
    }

    @Override
    public void subscribe(User user){
        if(isSubscriber(user)){
            this.notifyService.removeSubscriber(user);
        }else{
            this.notifyService.addSubscriber(user);
        }
    }

    @Override
    public void notify(String details){
        String info = "Want to let you know that, " + details;
        this.notifyService.notifyCompanySubscribers(this.companyName, info);
    }

    private int dfsRemove(Company currentCompany, Company subCompany) {
        int numOfRemovedRef = 0;
        Iterator<Company> iterator = currentCompany.getChildrenCompany().iterator();
        while (iterator.hasNext()) {
            Company comp = iterator.next();
            numOfRemovedRef += dfsRemove(comp, subCompany);
            if (comp.equals(subCompany)) {
                iterator.remove();
                numOfRemovedRef++;
            }
        }
        return numOfRemovedRef;
    }

    @Override
    public boolean isSubscriber(User user) {
        return this.notifyService.isSubscriber(user);
    }
}

