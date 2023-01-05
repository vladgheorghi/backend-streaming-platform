package page.pageList;
import page.Page;

import java.util.ArrayList;
import java.util.Map;

import static database.Constant.HOMEPAGE_LOGGED;
import static database.Constant.LOGOUT_PAGE;
import static database.Constant.MOVIES_PAGE;
import static database.Constant.UPGRADES_PAGE;

/**
 * @class class for creating the Homepage Logged page
 * */

public class HomepageLogged {
    /**
     * @return the created page
     * */
    public static Page createPage() {
        Page homepageLogged = new Page();

        homepageLogged.setName(HOMEPAGE_LOGGED);
        homepageLogged.setFeatures(new ArrayList<>());

        return homepageLogged;
    }

    /**
     * initialize the next pages list
     * */
    public static void initNextPages(final Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        nextPages.add(pageMap.get(LOGOUT_PAGE));
        nextPages.add(pageMap.get(MOVIES_PAGE));
        nextPages.add(pageMap.get(UPGRADES_PAGE));

        pageMap.get(HOMEPAGE_LOGGED).setNextPages(nextPages);
    }
}
