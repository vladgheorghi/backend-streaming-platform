package page.pageList;

import page.Page;

import java.util.ArrayList;
import java.util.Map;

import static database.Constant.REGISTER_PAGE;
import static database.Constant.REGISTER;

/**
 * @class class for creating the Register page
 * */

public class PageRegister {
    public static Page createPage() {
        /**
         * @return the created page
         * */
        Page pageRegister = new Page();

        pageRegister.setName(REGISTER_PAGE);

        ArrayList<String> features = new ArrayList<>();
        features.add(REGISTER);

        pageRegister.setFeatures(features);

        return pageRegister;
    }

    /**
     * initialize the next pages list
     * */
    public static void initNextPages(final Map<String, Page> pageMap) {
        ArrayList<Page> nextPages = new ArrayList<>();

        pageMap.get(REGISTER_PAGE).setNextPages(nextPages);
    }
}
