public enum CompanyPrefix {
    FLIGHT_ID("FLC-");

    private final String prefix;

    CompanyPrefix(String companyIdPrefix){
        this.prefix = companyIdPrefix;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
