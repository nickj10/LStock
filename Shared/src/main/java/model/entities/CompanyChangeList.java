package model.entities;

public class CompanyChangeList extends TunnelObject {

    private int[] companyId;
    private String[] companyName;
    private float[] companyCurrentShare;
    private float[] companyChange;
    private float[] companyChangePer;

    public CompanyChangeList(int numCompanies) {
        this.companyId = new int[numCompanies];
        this.companyName = new String[numCompanies];
        this.companyCurrentShare = new float[numCompanies];
        this.companyChange = new float[numCompanies];
        this.companyChangePer = new float[numCompanies];
    }

    public CompanyChangeList() {
    }

    public int[] getCompanyId() {
        return companyId;
    }

    public String[] getCompanyName() {
        return companyName;
    }

    public float[] getCompanyCurrentShare() {
        return companyCurrentShare;
    }

    public float[] getCompanyChange() {
        return companyChange;
    }

    public float[] getCompanyChangePer() {
        return companyChangePer;
    }

    public void setCompanyId(int i, int companyId) {
        this.companyId[i] = companyId;
    }

    public void setCompanyName(int i, String companyName) {
        this.companyName[i] = companyName;
    }

    public void setCompanyCurrentShare(int i, float companyShares) {
        this.companyCurrentShare[i] = companyShares;
    }

    public void setCompanyChange(int i, float companyChange) {
        this.companyChange[i] = companyChange;
    }

    public void setCompanyChangePer(int i, float companyChangePer) {
        this.companyChangePer[i] = companyChangePer;
    }
}
