package page.pagelist;
import page.Page;
import page.createpage.VisitorCreatePage;
import page.initnextpages.VisitorInitNextPages;

import java.util.Map;

/**
 * @class class for creating the Homepage Logged page
 * */

public class HomepageLogged extends Page {
    /***/
    @Override
    public void accept(final VisitorCreatePage visitor) {
        visitor.createPage(this);
    }
    /***/
    @Override
    public void accept(final VisitorInitNextPages visitor, final Map<String, Page> pageMap) {
        visitor.initNextPages(this, pageMap);
    }
}
