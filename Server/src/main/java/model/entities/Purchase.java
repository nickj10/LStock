package model.entities;

public class Purchase {
    private int userId;
    private int companyId;
    private int shareId;
    private int shareQuantity;

    public Purchase(){

    }

    public Purchase(int userId, int companyId, int shareId, int shareQuantity) {
        this.userId = userId;
        this.companyId = companyId;
        this.shareId = shareId;
        this.shareQuantity = shareQuantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getShareQuantity() {
        return shareQuantity;
    }

    public void setShareQuantity(int shareQuantity) {
        this.shareQuantity = shareQuantity;
    }
}
