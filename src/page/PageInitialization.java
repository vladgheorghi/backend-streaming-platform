package page;

import page.createpage.CreatePage;
import page.initnextpages.InitNextPages;
import page.pagelist.PageRegister;
import page.pagelist.PageLogin;
import page.pagelist.PageLogout;
import page.pagelist.PageMovies;
import page.pagelist.PageUpgrades;
import page.pagelist.PageSeeDetails;
import page.pagelist.HomepageLogged;
import page.pagelist.HomepageUnlogged;

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

        CreatePage visitorCreatePage = new CreatePage();

        // creates all pages and puts them inside the page map
        pageMap.put(HOMEPAGE_UNLOGGED, new HomepageUnlogged());
        pageMap.put(LOGIN_PAGE, new PageLogin());
        pageMap.put(REGISTER_PAGE, new PageRegister());
        pageMap.put(HOMEPAGE_LOGGED, new HomepageLogged());
        pageMap.put(LOGOUT_PAGE, new PageLogout());
        pageMap.put(MOVIES_PAGE, new PageMovies());
        pageMap.put(SEE_DETAILS_PAGE, new PageSeeDetails());
        pageMap.put(UPGRADES_PAGE, new PageUpgrades());

        for (Map.Entry<String, Page> entry : pageMap.entrySet()) {
            entry.getValue().accept(visitorCreatePage);
        }

        // initialize the next page list for all pages
        InitNextPages visitorInitNextPages = new InitNextPages();

        for (Map.Entry<String, Page> entry : pageMap.entrySet()) {
            entry.getValue().accept(visitorInitNextPages, pageMap);
        }

        return PageDatabase.getInstance(pageMap);
    }
}
