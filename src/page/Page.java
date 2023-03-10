package page;

import page.createpage.VisitorCreatePage;
import page.initnextpages.VisitorInitNextPages;

import java.util.ArrayList;
import java.util.Map;

/**
 * @class class for page data
 * @details contains info about a page
 * */

public abstract class Page {
    private String name; /** name of the page */
    private ArrayList<String> features = new ArrayList<>(); /** list with features the page has */
    private ArrayList<Page> nextPages; /** list with next pages that can be accessed from page */

    /***/
    public abstract void accept(VisitorCreatePage visitor);
    /***/
    public abstract void accept(VisitorInitNextPages visitor, Map<String, Page> pageMap);

    /** Getters */
    public final String getName() {
        return name;
    }
    public final ArrayList<String> getFeatures() {
        return features;
    }
    public final ArrayList<Page> getNextPages() {
        return nextPages;
    }

    /** Setters */
    public final void setName(final String name) {
        this.name = name;
    }
    public final void setFeatures(final ArrayList<String> features) {
        this.features = features;
    }
    public final void setNextPages(final ArrayList<Page> nextPages) {
        this.nextPages = nextPages;
    }
}
