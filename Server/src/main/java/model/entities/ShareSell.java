package model.entities;

/**
 * This class has the info of the shares the user has. This information is shown in the CompanyDetailView
 */
public class ShareSell {

    private int shareId;
    private float shareValue;
    private int shareQuantity;

    /**
     * Empty constructor of ShareSell
     */
    public ShareSell() {
    }

    /**
     * It will create a shareSell
     *
     * @param shareId       share id
     * @param shareValue    the value that share had when the user bought it
     * @param shareQuantity the quantity that the user has of this share
     */
    public ShareSell(int shareId, float shareValue, int shareQuantity) {
        this.shareId = shareId;
        this.shareValue = shareValue;
        this.shareQuantity = shareQuantity;
    }

    /**
     * Getters and setters
     */
    public float getShareValue() {
        return shareValue;
    }

    public void setShareValue(float shareValue) {
        this.shareValue = shareValue;
    }

    public int getShareQuantity() {
        return shareQuantity;
    }

    public void setShareQuantity(int shareQuantity) {
        this.shareQuantity = shareQuantity;
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }
}



