// Flexible prefix for company ID might want to add other companys with different IDS prefix's

public enum PrefixCompanyID {
    FLIGHT_ID("FLC-");

    private final String prefix;

    PrefixCompanyID(String companyIdPrefix){
        this.prefix = companyIdPrefix;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
