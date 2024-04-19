import java.util.HashSet;
import java.util.Set;

public class ConcreteCompany implements Company{

    private static char KeyID = 'A';

    protected final String companyID;
    protected final String companyName;
    protected Set<Company> subCompanies;

    public ConcreteCompany(String companyID, String companyName){
        this.companyID = companyID + KeyID;
        this.companyName = companyName;
        this.subCompanies = new HashSet<>();
        KeyID++;
    }

    @Override
    public void addCompany(Company company) {
        if(this.isSubCompanyOf(company)){
            System.out.println("Fail to add Sub-Company, "+ this.companyName + "is Sub Company of" + company.getCompanyName());
        }else subCompanies.add(company);
    }

    @Override
    public boolean removeCompany(Company company) {
        if(!subCompanies.remove(company)){
            System.out.println(company.getCompanyName() + "Remove fail, might be a sub of sub company, for a full check to remove use removeDeepSubCompany");
            return false;
        }
        return true;
    }

    @Override
    public String getCompanyID(){
        return this.companyID;
    }

    @Override
    public String getCompanyName(){
        return this.companyName;
    }

    @Override
    public Set<Company> getCompanies() {
        return this.subCompanies;
    }

    @Override
    public boolean isSubCompanyOf(Company company) {
        if(company.getCompanyID().equals(this.companyID)) return true;
        if(company.getCompanies().isEmpty()) return false;

        boolean isSub = false;
        for(Company comp : company.getCompanies()){
            isSub |= this.isSubCompanyOf(comp);
        }

        return isSub;
    }

    @Override
    public int removeDeepSubCompany(Company subCompany) {
        int numOfRemovedRef = 0;
        if (subCompany.isSubCompanyOf(this)) {
            for (Company comp : this.getCompanies()) {
                if (subCompany.isSubCompanyOf(comp)) {
                    comp.removeCompany(subCompany);
                    numOfRemovedRef++;
                    numOfRemovedRef += comp.removeDeepSubCompany(subCompany);
                }
            }
        }
        return numOfRemovedRef;
    }

}
