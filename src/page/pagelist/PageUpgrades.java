package page.pageList;

import page.Page;

import java.util.ArrayList;
import java.util.Map;

import static database.Constant.UPGRADES_PAGE;
import static database.Constant.BUY_TOKENS;
import static database.Constant.BUY_PREMIUM_ACCOUNT;
import static database.Constant.HOMEPAGE_LOGGED;
import static database.Constant.MOVIES_PAGE;
import static database.Constant.LOGOUT_PAGE;

/**
 * @class class for creating the Upgrades page
 * */

public class PageUpgrades {
    /**
     * @return the created page
     * */
    public static Page createPage() {
        Page pageUpgrades = new Page();

        pageUpgrades.setName(UPGRADES_PAGE);

        ArrayList<String> features = new ArrayList<>();
        features.add(BUY_TOKENS);
        features.add(BUY_PREMIUM_ACCOUNT);

        pageUpgrades.setFeatures(features);

        return pageUpgrades;
    }

    /**
     * initialize the next pages list
     * */
    public static void initNextPages(final Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();
        nextPages.add(pageMap.get(HOMEPAGE_LOGGED));
        nextPages.add(pageMap.get(MOVIES_PAGE));
        nextPages.add(pageMap.get(LOGOUT_PAGE));

        pageMap.get(UPGRADES_PAGE).setNextPages(nextPages);
    }
}
