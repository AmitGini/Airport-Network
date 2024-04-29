
// using Composite Design Pattern and inheritance
public abstract class Employee extends Customer{

    protected Company myCompany;

    public Employee(Company company, String username, String password){
        super(username, password);
        this.myCompany = company;
    }

    public Company getMyCompany() {
        return this.myCompany;
    }

    public boolean flightActionVerfication(Company company){
        boolean isEmployeeVerified = this.myCompany.isChildOf(company) || this.myCompany.getID().equals(company.getID());
        if(!isEmployeeVerified) System.out.println(this.myUsername + "is Not " + company.getName() + "Employee");
        return isEmployeeVerified;
    }
}
