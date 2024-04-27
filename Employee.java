public abstract class Employee<T extends Company> extends Customer{

    protected T myCompany;

    public Employee(T company, String username, String password){
        super(username, password);
        this.myCompany = company;
    }

    public T getMyCompany() {
        return this.myCompany;
    }

    public boolean flightActionVerfication(T company){
        boolean isEmployeeVerified = this.myCompany.isChildOf(company) || this.myCompany.getID().equals(company.getID());
        if(!isEmployeeVerified) System.out.println(this.myUsername + "is Not " + company.getName() + "Employee");
        return isEmployeeVerified;
    }
}
