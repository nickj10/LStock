package utils;

import model.entities.*;
import utils.mappers.ShareMapper;

import java.util.ArrayList;

public class ShareMapperImpl implements ShareMapper {

    @Override
    public Purchase[] shareTradeToPurchase(ShareTrade shareTrade) {
        int numPurchases = shareTrade.getShareId().length;
        Purchase[] purchases = new Purchase[numPurchases];
        for (int i = 0; i < numPurchases; i++) {
            purchases[i] = new Purchase();
            purchases[i].setUserId(shareTrade.getUserId());
            purchases[i].setCompanyId(shareTrade.getCompanyId());
            purchases[i].setShareId(shareTrade.getShareId()[i]);
            purchases[i].setShareQuantity(shareTrade.getNumShares()[i]);
        }
        return purchases;
    }

    @Override
    public User shareTradeToUser(ShareTrade shareTrade) {
        User user = new User();
        user.setUserId(shareTrade.getUserId());
        user.setTotalBalance(shareTrade.getTotalBalance());
        return user;
    }

    @Override
    public ShareTrade userToShareTrade(User user) {
        ShareTrade shareTrade = new ShareTrade();
        shareTrade.setUserId(user.getUserId());
        shareTrade.setTotalBalance(user.getTotalBalance());
        return shareTrade;
    }

    @Override
    public Company shareTradeToCompany(ShareTrade shareTrade) {
        Company company = new Company();
        company.setCompanyId(shareTrade.getCompanyId());
        company.setValue(shareTrade.getSharePrice()[0]);
        return company;
    }

    @Override
    public ShareTrade companyToShareTrade(Company company) {
        ShareTrade shareTrade = new ShareTrade();
        shareTrade.setCompanyId(company.getCompanyId());
        return shareTrade;
    }

    @Override
    public ShareTrade userCompanyToShareTrade(User user, Company company) {
        ShareTrade shareTrade = new ShareTrade();
        shareTrade.setUserId(user.getUserId());
        shareTrade.setTotalBalance(user.getTotalBalance());
        shareTrade.setCompanyId(company.getCompanyId());
        return shareTrade;
    }

    @Override
    public ArrayList<ArrayList<ShareSell>> converToSharesSell(ArrayList<ShareSellList> shareSellList) {
        ArrayList<ArrayList<ShareSell>> sharesSells = new ArrayList<ArrayList<ShareSell>>();
        for(int n=0; n< shareSellList.size(); n++){
            ArrayList<ShareSell> shares = new ArrayList<ShareSell>();
            int sharesLen = shareSellList.get(n).getShareQuantity().length;
            int[] shareId = shareSellList.get(n).getShareId();
            float[] sharesValue = shareSellList.get(n).getShareValue();
            int[] sharesQuantity = shareSellList.get(n).getShareQuantity();
            for (int i = 0; i < sharesLen; i++) {
                shares.add(new ShareSell(shareId[i], sharesValue[i], sharesQuantity[i]));
            }
            sharesSells.add(shares);
        }
        return sharesSells;
    }

    @Override
    public ArrayList<ShareSellList> convertToShareSellList(ArrayList<ArrayList<ShareSell>> shareSells) {
        ArrayList<ShareSellList> sharesSells = new ArrayList<ShareSellList>();
        for(int n=0; n<shareSells.size(); n++){
            ShareSellList shareSellList = new ShareSellList(shareSells.size());
            int i = 0;
            for (ShareSell s : shareSells.get(n)) {
                shareSellList.setShareId(i, s.getShareId());
                shareSellList.setShareValue(i, s.getShareValue());
                shareSellList.setShareQuantity(i, s.getShareQuantity());
                i++;
            }
            sharesSells.add(shareSellList);
        }
        return sharesSells;
    }

    @Override
    public ArrayList<ShareChange> convertToSharesChange(ShareChangeList shareChangeList) {
        ArrayList<ShareChange> shares = new ArrayList<ShareChange>();
        int sharesLen = shareChangeList.getCompanyName().length;
        int userId = shareChangeList.getUserId();
        int[] companyId = shareChangeList.getCompanyId();
        int[] sharesId = shareChangeList.getShareId();
        String[] companyNames = shareChangeList.getCompanyName();
        float[] shareOriginalValue = shareChangeList.getShareOriginalValue();
        float[] shareCurrentValue = shareChangeList.getShareCurrentValue();
        int[] sharesQuantity = shareChangeList.getSharesQuantity();
        float[] profitLoss = shareChangeList.getProfitLoss();
        for (int i = 0; i < sharesLen; i++) {
            shares.add(new ShareChange(userId, companyId[i], sharesId[i], companyNames[i], shareOriginalValue[i], shareCurrentValue[i], sharesQuantity[i], profitLoss[i]));
        }
        return shares;
    }

    @Override
    public ShareChangeList convertToShareChangeList(ArrayList<ShareChange> sharesChange) {
        ShareChangeList shareChangeList = new ShareChangeList(sharesChange.size());
        if (sharesChange.size() > 0) {
            shareChangeList.setUserId(sharesChange.get(0).getUserId());
            int i = 0;
            for (ShareChange s : sharesChange) {
                shareChangeList.setCompanyId(i, s.getCompanyId());
                shareChangeList.setShareId(i, s.getShareId());
                shareChangeList.setCompanyName(i, s.getCompanyName());
                shareChangeList.setShareOriginalValue(i, s.getShareOriginalValue());
                shareChangeList.setShareCurrentValue(i, s.getShareCurrentValue());
                shareChangeList.setSharesQuantity(i, s.getSharesQuantity());
                shareChangeList.setProfitLoss(i, s.getProfitLoss());
                i++;
            }
        }
        return shareChangeList;
    }
}
