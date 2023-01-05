package page;

import java.util.Map;

/**
 * @class class for page system
 * @details creates the page hierarchy following the 'Singleton Design Pattern' because only one
 * instance of the page hierarchy should be created. This also is secured from multithreading
 * access using 'synchronized'
 * */

public final class PageDatabase {
    private static volatile PageDatabase instance = null; /** the single instance */
    private final Map<String, Page> pageMap; /** page hierarchy stored as a map for easy access */

    /**
     * @details private constructor accessed only from within 'getInstance()'
     * */
    private PageDatabase(final Map<String, Page> pageMap) {
        this.pageMap = pageMap;
    }

    /**
     * @details if the instance is not created, it creates it. if the instance is already created,
     * it returns that instance. This assures that no other instance can be created
     * */
    public static PageDatabase getInstance(final Map<String, Page> pageMap) {
        if (instance == null) {
            synchronized (PageDatabase.class) {
                if (instance == null) {
                    instance = new PageDatabase(pageMap);
                }
            }
        }
        return instance;
    }

    /** Getter for the page map */
    public Map<String, Page> getPageMap() {
        return pageMap;
    }
}
