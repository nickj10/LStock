package model.entities;

public class ThreadChange extends TunnelObject{
    private CompanyChangeList companyChangeList;
    private DetailViewInfo detailViewInfo;
    private ShareChangeList shareChangeList;
    private UserProfileInfo userProfileInfo;

    public ThreadChange(CompanyChangeList companyChangeList, DetailViewInfo detailViewInfo, ShareChangeList shareChangeList, UserProfileInfo userProfileInfo) {
        System.out.println("ThreadChange created.");
        this.companyChangeList = companyChangeList;
        this.detailViewInfo = detailViewInfo;
        this.shareChangeList = shareChangeList;
        this.userProfileInfo = userProfileInfo;
    }

    public ShareChangeList getShareChangeList() {
        return shareChangeList;
    }

    public void setShareChangeList(ShareChangeList shareChangeList) {
        this.shareChangeList = shareChangeList;
    }

    public DetailViewInfo getDetailViewInfo() {
        return detailViewInfo;
    }

    public void setDetailViewInfo(DetailViewInfo detailViewInfo) {
        this.detailViewInfo = detailViewInfo;
    }

    public CompanyChangeList getCompanyChangeList() {
        return companyChangeList;
    }

    public void setCompanyChangeList(CompanyChangeList companyChangeList) {
        this.companyChangeList = companyChangeList;
    }

    public UserProfileInfo getUserProfileInfo() {
        return userProfileInfo;
    }

    public void setUserProfileInfo(UserProfileInfo userProfileInfo) {
        this.userProfileInfo = userProfileInfo;
    }
}
