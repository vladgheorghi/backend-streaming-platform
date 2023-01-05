package page;

import page.pageList.PageRegister;
import page.pageList.PageLogin;
import page.pageList.PageLogout;
import page.pageList.PageMovies;
import page.pageList.PageUpgrades;
import page.pageList.PageSeeDetails;
import page.pageList.HomepageLogged;
import page.pageList.HomepageUnlogged;

import java.util.HashMap;
import java.util.Map;

import static database.Constant.HOMEPAGE_UNLOGGED;
import static database.Constant.LOGIN_PAGE;
import static database.Constant.REGISTER_PAGE;
import static database.Constant.HOMEPAGE_LOGGED;
import static database.Constant.LOGOUT_PAGE;
import static database.Constant.MOVIES_PAGE;
import static database.Constant.SEE_DETAILS_PAGE;
import static database.Constant.UPGRADES_PAGE;

/**
 * @class class for page system creation
 * @details calls all methods that create all the pages
 * */

public class PageInitialization {
    /**
     * @return a page system with the page hierarchy (follows the 'Singleton Design Pattern')
     * */
    public static PageDatabase initializePageSystem() {
        Map<String, Page> pageMap = new HashMap<>();

        // creates all pages and puts them inside the page map
        pageMap.put(HOMEPAGE_UNLOGGED, HomepageUnlogged.createPage());
        pageMap.put(LOGIN_PAGE, PageLogin.createPage());
        pageMap.put(REGISTER_PAGE, PageRegister.createPage());
        pageMap.put(HOMEPAGE_LOGGED, HomepageLogged.createPage());
        pageMap.put(LOGOUT_PAGE, PageLogout.createPage());
        pageMap.put(MOVIES_PAGE, PageMovies.createPage());
        pageMap.put(SEE_DETAILS_PAGE, PageSeeDetails.createPage());
        pageMap.put(UPGRADES_PAGE, PageUpgrades.createPage());

        // initialize the next page list for all pages
        HomepageUnlogged.initNextPages(pageMap);
        PageLogin.initNextPages(pageMap);
        PageRegister.initNextPages(pageMap);
        HomepageLogged.initNextPages(pageMap);
        PageLogout.initNextPages(pageMap);
        PageMovies.initNextPages(pageMap);
        PageSeeDetails.initNextPages(pageMap);
        PageUpgrades.initNextPages(pageMap);

        return PageDatabase.getInstance(pageMap);
    }
}
