import java.util.Set;

public interface Company {


    public void addCompany(Company company);
    public boolean removeCompany(Company company);
    public Set<Company> getCompanies();
    public String getCompanyID();
    public String getCompanyName();
    public boolean isSubCompanyOf(Company company);
    public int removeDeepSubCompany(Company subCompany);


}