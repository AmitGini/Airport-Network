public abstract class Employee extends Customer{

    protected Company myCompany;

    public Employee(Company company, String username, String password){
        super(username, password);
        this.myCompany = company;
    }

    public Company getMyCompany() {
        return this.myCompany;
    }
}
