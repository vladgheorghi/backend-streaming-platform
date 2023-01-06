package page.createpage;

import page.pagelist.*;

import java.util.ArrayList;

import static database.Constant.*;

/**
 * @class implementations of each function from the interface declarations
 * */
public class CreatePage implements VisitorCreatePage {
    /***/
    @Override
    public void createPage(final HomepageLogged page) {
        page.setName(HOMEPAGE_LOGGED);
        page.setFeatures(new ArrayList<>());
    }
    /***/
    @Override
    public void createPage(final HomepageUnlogged page) {
        page.setName(HOMEPAGE_UNLOGGED);
        page.setFeatures(new ArrayList<>());
    }
    /***/
    @Override
    public void createPage(final PageLogin page) {
        page.setName(LOGIN_PAGE);

        ArrayList<String> features = new ArrayList<>();
        features.add(LOGIN);

        page.setFeatures(features);
    }
    /***/
    @Override
    public void createPage(final PageLogout page) {
        page.setName(LOGOUT_PAGE);
        page.setFeatures(new ArrayList<>());
    }
    /***/
    @Override
    public void createPage(final PageMovies page) {
        page.setName(MOVIES_PAGE);

        ArrayList<String> features = new ArrayList<>();
        features.add(SEARCH);
        features.add(FILTER);

        page.setFeatures(features);
    }
    /***/
    @Override
    public void createPage(final PageRegister page) {
        page.setName(REGISTER_PAGE);

        ArrayList<String> features = new ArrayList<>();
        features.add(REGISTER);

        page.setFeatures(features);
    }
    /***/
    @Override
    public void createPage(final PageSeeDetails page) {
        page.setName(SEE_DETAILS_PAGE);

        ArrayList<String> features = new ArrayList<>();
        features.add(PURCHASE);
        features.add(WATCH);
        features.add(LIKE);
        features.add(RATE);
        features.add(SUBSCRIBE);

        page.setFeatures(features);
    }
    /***/
    @Override
    public void createPage(final PageUpgrades page) {
        page.setName(UPGRADES_PAGE);

        ArrayList<String> features = new ArrayList<>();
        features.add(BUY_TOKENS);
        features.add(BUY_PREMIUM_ACCOUNT);

        page.setFeatures(features);
    }
}
