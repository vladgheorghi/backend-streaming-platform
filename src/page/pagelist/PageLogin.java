package page.pagelist;

import page.Page;
import page.createpage.VisitorCreatePage;
import page.initnextpages.VisitorInitNextPages;

import java.util.Map;

/**
 * @class class for creating the Login page
 * */

public class PageLogin extends Page {
    @Override
    public void accept(VisitorCreatePage visitor) {
        visitor.createPage(this);
    }

    @Override
    public void accept(VisitorInitNextPages visitor, Map<String, Page> pageMap) {
        visitor.initNextPages(this, pageMap);
    }
}
