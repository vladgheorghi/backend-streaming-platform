package page.initnextpages;

import page.Page;
import page.pagelist.*;

import java.util.ArrayList;
import java.util.Map;

import static database.Constant.*;
import static database.Constant.HOMEPAGE_LOGGED;

/**
 * @class implementation of each function from the interface declarations
 * */
public class InitNextPages implements VisitorInitNextPages {
    /***/
    @Override
    public void initNextPages(HomepageLogged page, Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        nextPages.add(pageMap.get(LOGOUT_PAGE));
        nextPages.add(pageMap.get(MOVIES_PAGE));
        nextPages.add(pageMap.get(UPGRADES_PAGE));

        page.setNextPages(nextPages);
    }
    /***/
    @Override
    public void initNextPages(HomepageUnlogged page, Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        nextPages.add(pageMap.get(REGISTER_PAGE));
        nextPages.add(pageMap.get(LOGIN_PAGE));

        page.setNextPages(nextPages);
    }
    /***/
    @Override
    public void initNextPages(PageLogin page, Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        page.setNextPages(nextPages);
    }
    /***/
    @Override
    public void initNextPages(PageLogout page, Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        nextPages.add(pageMap.get(HOMEPAGE_UNLOGGED));

        page.setNextPages(nextPages);
    }
    /***/
    @Override
    public void initNextPages(PageMovies page, Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        nextPages.add(pageMap.get(HOMEPAGE_LOGGED));
        nextPages.add(pageMap.get(LOGOUT_PAGE));
        nextPages.add(pageMap.get(SEE_DETAILS_PAGE));
        nextPages.add(pageMap.get(MOVIES_PAGE));

        page.setNextPages(nextPages);
    }
    /***/
    @Override
    public void initNextPages(PageRegister page, Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        page.setNextPages(nextPages);
    }
    /***/
    @Override
    public void initNextPages(PageSeeDetails page, Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        nextPages.add(pageMap.get(HOMEPAGE_LOGGED));
        nextPages.add(pageMap.get(MOVIES_PAGE));
        nextPages.add(pageMap.get(UPGRADES_PAGE));
        nextPages.add(pageMap.get(LOGOUT_PAGE));

        page.setNextPages(nextPages);
    }
    /***/
    @Override
    public void initNextPages(PageUpgrades page, Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        nextPages.add(pageMap.get(HOMEPAGE_LOGGED));
        nextPages.add(pageMap.get(MOVIES_PAGE));
        nextPages.add(pageMap.get(LOGOUT_PAGE));

        page.setNextPages(nextPages);
    }
}
