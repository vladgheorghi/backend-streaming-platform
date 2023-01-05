package page.pageList;

import page.Page;

import java.util.ArrayList;
import java.util.Map;

import static database.Constant.MOVIES_PAGE;
import static database.Constant.SEARCH;
import static database.Constant.FILTER;
import static database.Constant.HOMEPAGE_LOGGED;
import static database.Constant.LOGOUT_PAGE;
import static database.Constant.SEE_DETAILS_PAGE;

/**
 * @class class for creating the Movies page
 * */

public class PageMovies {
    /**
     * @return the created page
     * */
    public static Page createPage() {
        Page pageMovies = new Page();

        pageMovies.setName(MOVIES_PAGE);

        ArrayList<String> features = new ArrayList<>();
        features.add(SEARCH);
        features.add(FILTER);

        pageMovies.setFeatures(features);

        return pageMovies;
    }

    /**
     * initialize the next pages list
     * */
    public static void initNextPages(final Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();
        nextPages.add(pageMap.get(HOMEPAGE_LOGGED));
        nextPages.add(pageMap.get(LOGOUT_PAGE));
        nextPages.add(pageMap.get(SEE_DETAILS_PAGE));
        nextPages.add(pageMap.get(MOVIES_PAGE));

        pageMap.get(MOVIES_PAGE).setNextPages(nextPages);
    }
}
