package page.pageList;

import page.Page;

import java.util.ArrayList;
import java.util.Map;

import static database.Constant.LOGIN_PAGE;
import static database.Constant.LOGIN;

/**
 * @class class for creating the Login page
 * */

public class PageLogin {
    /**
     * @return the created page
     * */
    public static Page createPage() {
        Page pageLogin = new Page();

        pageLogin.setName(LOGIN_PAGE);

        ArrayList<String> features = new ArrayList<>();
        features.add(LOGIN);

        pageLogin.setFeatures(features);

        return pageLogin;
    }

    /**
     * initialize the next pages list
     * */
    public static void initNextPages(final Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        pageMap.get(LOGIN_PAGE).setNextPages(nextPages);
    }
}
