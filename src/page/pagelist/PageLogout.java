package page.pageList;

import page.Page;

import java.util.ArrayList;
import java.util.Map;

import static database.Constant.LOGOUT_PAGE;
import static database.Constant.HOMEPAGE_UNLOGGED;

/**
 * @class class for creating the Homepage Logout page
 * */

public class PageLogout {
    /**
     * @return the created page
     * */
    public static Page createPage() {
        Page pageLogout = new Page();

        pageLogout.setName(LOGOUT_PAGE);
        pageLogout.setFeatures(new ArrayList<>());

        return pageLogout;
    }

    /**
     * initialize the next pages list
     * */
    public static void initNextPages(final Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();
        nextPages.add(pageMap.get(HOMEPAGE_UNLOGGED));

        pageMap.get(LOGOUT_PAGE).setNextPages(nextPages);
    }
}
