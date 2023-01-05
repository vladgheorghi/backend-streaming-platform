package page.pageList;

import page.Page;

import java.util.ArrayList;
import java.util.Map;

import static database.Constant.SEE_DETAILS_PAGE;
import static database.Constant.PURCHASE;
import static database.Constant.WATCH;
import static database.Constant.LIKE;
import static database.Constant.RATE;
import static database.Constant.HOMEPAGE_LOGGED;
import static database.Constant.MOVIES_PAGE;
import static database.Constant.UPGRADES_PAGE;
import static database.Constant.LOGOUT_PAGE;

/**
 * @class class for creating the See Details page
 * */

public class PageSeeDetails {
    /**
     * @return the created page
     * */
    public static Page createPage() {
        Page pageSeeDetails = new Page();

        pageSeeDetails.setName(SEE_DETAILS_PAGE);

        ArrayList<String> features = new ArrayList<>();
        features.add(PURCHASE);
        features.add(WATCH);
        features.add(LIKE);
        features.add(RATE);

        pageSeeDetails.setFeatures(features);

        return pageSeeDetails;
    }

    /**
     * initialize the next pages list
     * */
    public static void initNextPages(final Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();
        nextPages.add(pageMap.get(HOMEPAGE_LOGGED));
        nextPages.add(pageMap.get(MOVIES_PAGE));
        nextPages.add(pageMap.get(UPGRADES_PAGE));
        nextPages.add(pageMap.get(LOGOUT_PAGE));

        pageMap.get(SEE_DETAILS_PAGE).setNextPages(nextPages);
    }
}
