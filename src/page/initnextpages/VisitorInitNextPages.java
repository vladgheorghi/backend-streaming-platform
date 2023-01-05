package page.initnextpages;

import page.Page;
import page.pagelist.*;

import java.util.Map;

/**
 * @class interface for initializing the next available pages list for each page
 * */
public interface VisitorInitNextPages {
    /***/
    void initNextPages(HomepageLogged page, Map<String, Page> pageMap);
    /***/
    void initNextPages(HomepageUnlogged page, Map<String, Page> pageMap);
    /***/
    void initNextPages(PageLogin page, Map<String, Page> pageMap);
    /***/
    void initNextPages(PageLogout page, Map<String, Page> pageMap);
    /***/
    void initNextPages(PageMovies page, Map<String, Page> pageMap);
    /***/
    void initNextPages(PageRegister page, Map<String, Page> pageMap);
    /***/
    void initNextPages(PageSeeDetails page, Map<String, Page> pageMap);
    /***/
    void initNextPages(PageUpgrades page, Map<String, Page> pageMap);
}
