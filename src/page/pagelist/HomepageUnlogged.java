package page.pageList;

import page.Page;

import java.util.ArrayList;
import java.util.Map;

import static database.Constant.HOMEPAGE_UNLOGGED;
import static database.Constant.REGISTER_PAGE;
import static database.Constant.LOGIN_PAGE;

/**
 * @class class for creating the Homepage Unlogged page
 * */

public class HomepageUnlogged {
    /**
     * @return the created page
     * */
    public static Page createPage() {
        Page homepageUnlogged = new Page();

        homepageUnlogged.setName(HOMEPAGE_UNLOGGED);
        homepageUnlogged.setFeatures(new ArrayList<>());

        return homepageUnlogged;
    }

    /**
     * initialize the next pages list
     * */
    public static void initNextPages(final Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();
        nextPages.add(pageMap.get(REGISTER_PAGE));
        nextPages.add(pageMap.get(LOGIN_PAGE));

        pageMap.get(HOMEPAGE_UNLOGGED).setNextPages(nextPages);
    }
}
