package model.managers;

import database.CompanyDao;
import database.ShareDao;
import database.UserDao;
import model.entities.*;
import database.DBConnector;
import utils.ShareMapperImpl;
import utils.UserMapperImpl;

import java.util.ArrayList;

public class StockManager {
    private ArrayList<Company> companies;
    private ArrayList<CompanyChange> companiesChange;
    private ArrayList<ShareChange> sharesChange;
    private ArrayList<CompanyDetail> companyDetails;
    private UserDao userDao;
    private CompanyDao companyDao;
    private ShareDao shareDao;
    private UserMapperImpl mapper;
    private ShareMapperImpl shareMapper;

    /**
     * Constructor for a StockManager
     */
    public StockManager() {
        DBConnector connector = new DBConnector();
        userDao = new UserDao(connector);
        shareDao = new ShareDao(connector);
        companyDao = new CompanyDao(connector);
        mapper = new UserMapperImpl();
        shareMapper = new ShareMapperImpl();
        connector.connect();
    }

    /**
     * Constructor for a StockManager
     *
     * @param userDao Dao for the User Table
     * @param companyDao Dao for the Company Table
     */
    public StockManager(UserDao userDao, CompanyDao companyDao) {
        this.userDao = userDao;
        this.companyDao = companyDao;
        this.companies = new ArrayList<Company>();
        this.companiesChange = new ArrayList<CompanyChange>();
        this.sharesChange = new ArrayList<ShareChange>();
    }

    /**
     * Function that registers a user if the conditions are met
     *
     * @param user the user
     * @return Authentification with the information we need to send for the client
     */
    public AuthenticationInfo registerUser(User user) {
        String response = userDao.createUser(user);
        AuthenticationInfo info = mapper.userToAuthenticationInfo(user);
        if (!response.equals("Register Success")) {
            info.setValidated(false);
        } else {
            info.setValidated(true);
        }
        info.setAction("register");
        info.setResponseType(response);
        return info;
    }

    /**
     * Validates the user
     *
     * @param user The user
     * @return AuthenticationInfo with the validated users information
     */
    public AuthenticationInfo validateUser(User user) {
        String response = userDao.validateUser(user);
        AuthenticationInfo info = mapper.userToAuthenticationInfo(user);
        if (response.equals("Login Success")) {
            info.setValidated(true);
        } else {
            info.setValidated(false);
        }
        info.setAction("login");
        info.setResponseType(response);
        return info;
    }

    /**
     * Updates the user new balanace
     *
     * @param user The user
     * @return UserProfileInfo with the updated information of the user
     */
    public UserProfileInfo updateUserBalance(User user) {
        userDao.updateUserBalanceLoad(user);
        UserProfileInfo info = mapper.userToUserProfileInfo(user);
        info.setAction("balance");
        return info;
    }

    /**
     * Updates the users description, for now.
     *
     * @param user The user
     * @return UserProfileInfo with the the update information of the user
     */
    public UserProfileInfo updateUserInformation(User user) {
        userDao.updateUserInformation(user);
        UserProfileInfo info = mapper.userToUserProfileInfo(user);
        info.setAction("information");
        return info;
    }

    /**
     * Gets the profile info of the user.
     *
     * @param user The user
     * @return UserProfileInfo with the the update information of the user
     */
    public UserProfileInfo getUserProfileInfo(User user) {
        userDao.getUserProfileInfo(user);
        UserProfileInfo info = mapper.userToUserProfileInfo(user);
        info.setAction("profileView");
        return info;
    }

    /**
     * Creates a new share between company and user.
     *
     * @param user    the user
     * @param company the company
     * @return ShareTrade with the new values of users total balance and company value
     */
    public ShareTrade updatePurchaseBuy(User user, Company company, Purchase[] purchases, String action, String view) {
        //Updates the user balance
        userDao.updateUserBalance(user);
        //If the action is Sell, we want to decrease the number of shares.
        if (action.equals("BUY")) {
            //Updates the purchased share
            shareDao.updatePurchasedShare(purchases[0]);
        } else {
            for (int i = 0; i < purchases.length; i++) {
                purchases[i].setShareQuantity(-purchases[i].getShareQuantity());
                //Updates the purchased share
                shareDao.updatePurchasedShare(purchases[i]);
            }
        }
        //Recalculates the new value of the company
        float currentValue = companyDao.getCompanyCurrenValue(company.getCompanyId());
        company.setValue(currentValue);
        company.recalculateValue(action);

        //Updates the company new value
        companyDao.updateCompanyNewValue(company);
        ShareTrade info = shareMapper.userCompanyToShareTrade(user, company);
        info.setView(view);
        return info;
    }

    public void buyShare(Purchase purchase) {
        shareDao.updatePurchasedShare(purchase);
    }

    public int getShareId(int id) {
        return shareDao.getCurrentShareId(id);
    }

    /**
     * Returns an Arraylist with all the companies
     *
     * @return ArrayList<Company> with all the companies
     */
    public ArrayList<Company> getCompanies() {
        companies = companyDao.getAllCompanies();
        return companies;
    }

    /**
     * Gets all the companies in the LStock, with their name, current share price and
     * the difference between the current price and the one that had 5 minutes ago
     *
     * @return ArrayList<CompanyChange> a list of the information mentioned before
     */
    public ArrayList<CompanyChange> getCompaniesChange() {
        companiesChange = companyDao.getCompaniesChange();
        return companiesChange;
    }

    /**
     * Returns an Arraylist with the Top 10 Companies in share value
     *
     * @return ArrayList<Top10> with Top 10 Companies
     */
    public ArrayList<Top10> getTopTenlist(){
        return companyDao.getTopTen();
    }


    public ArrayList<ShareChange> getSharesChange(int userId) {
        sharesChange = shareDao.getSharesChange(userId);
        return sharesChange;
    }

    public ArrayList<CompanyDetail> getCompanyDetails(int userId, int companyId) {
        companyDetails = companyDao.getCompanyDetails(userId, companyId);
        return companyDetails;
    }

    public ArrayList<ShareSell> getSharesSell(int userId, int companyId) {
        return shareDao.getSharesSell(userId, companyId);
    }
}

