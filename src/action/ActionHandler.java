package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import database.DatabaseActions;
import output.OutputHandler;
import page.GenreSubscribe;
import page.Page;
import page.PageAccessFeature;
import page.PageChange;
import user.Handler;

import static database.Constant.CHANGE_PAGE;
import static database.Constant.ON_PAGE;
import static database.Constant.DATABASE;
import static database.Constant.BACK;
import static database.Constant.SUBSCRIBE;

/**
 * @class class for handling each action case
 */
public class ActionHandler {

    /**
     * @param mainDatabase -> main database with all the data
     * @param handler -> main handler (has current user, action etc.)
     * @param output -> node that contains all nodes in the output JSON folder
     * @param objectMapper -> main object mapper for creating JSON nodes and arrays
     */
    public static void handleAction(final Database mainDatabase, final Handler handler,
                                    final ArrayNode output, final ObjectMapper objectMapper) {
        switch (handler.getCurrentAction().getType()) {
            case CHANGE_PAGE: // change page if possible
                Page nextPage = mainDatabase.getPageMap().get(handler.getCurrentAction().getPage());

                PageChange.changePage(mainDatabase, handler, nextPage, output, objectMapper, false);
                break;
            case ON_PAGE: // applies a feature on the current page
                if (handler.getCurrentPage().getFeatures()
                        .contains(handler.getCurrentAction().getFeature())) {
                    PageAccessFeature.accessPageFeature(mainDatabase,
                            handler, output, objectMapper);
                    return;
                }

                // outputs error in JSON file if page does not have this feature
                output.add(OutputHandler.outputHandler(handler, true, objectMapper));
                break;
            case SUBSCRIBE: // subscribe to a genre in the see details page
                GenreSubscribe.subscribe(handler, output, objectMapper);
                break;
            case DATABASE:
                DatabaseActions.databaseAction(mainDatabase, handler, output, objectMapper);
                break;
            case BACK:
                if (handler.getPreviousPages().size() == 0) {
                    output.add(OutputHandler.outputHandler(handler, true, objectMapper));
                    return;
                }

                int nrPrevPages = handler.getPreviousPages().size();
                String nextPageName = handler.getPreviousPages().get(nrPrevPages - 1);

                handler.getPreviousPages().remove(nrPrevPages - 1);
                Page backPage = mainDatabase.getPageMap().get(nextPageName);

                PageChange.changePage(mainDatabase, handler, backPage, output, objectMapper, true);
                break;
            default:
                break;
        }
    }
}
