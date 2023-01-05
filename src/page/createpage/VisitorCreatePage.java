package page.createpage;

import page.pagelist.*;

/**
 * @class interface for creating each page in the program
 * */
public interface VisitorCreatePage {
    /***/
    void createPage(HomepageLogged page);
    /***/
    void createPage(HomepageUnlogged page);
    /***/
    void createPage(PageLogin page);
    /***/
    void createPage(PageLogout page);
    /***/
    void createPage(PageMovies page);
    /***/
    void createPage(PageRegister page);
    /***/
    void createPage(PageSeeDetails page);
    /***/
    void createPage(PageUpgrades page);
}
