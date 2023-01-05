package page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import database.Database;

import movie.GetMovie;

import output.OutputHandler;

import user.Handler;

import java.util.ArrayList;

import static database.Constant.SEE_DETAILS_PAGE;
import static database.Constant.LOGOUT_PAGE;
import static database.Constant.HOMEPAGE_UNLOGGED;
import static database.Constant.MOVIES_PAGE;

/**
 * @class class for change page
 * @details used to change the current page to the next accessible page
 * */

public final class PageChange {
    /**
     * @param mainDatabase -> main database with all data
     * @param handler -> handler for the current user
     * @param nextPage -> the page that should be changed to if valid
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details changes the page to 'nextPage' if possible
     * */
    public static void changePage(final Database mainDatabase, final Handler handler,
                                  final Page nextPage, final ArrayNode output,
                                  final ObjectMapper objectMapper, final boolean back) {
        // first the next page is verified that it can be accessed from current page
        if (handler.getCurrentPage().getNextPages().contains(nextPage) || back) {
            if (handler.getCurrentUser() != null && !back) {
                handler.getPreviousPages().add(handler.getCurrentPage().getName());

                if (handler.getCurrentPage().getName().equals(SEE_DETAILS_PAGE)) {
                    handler.getSeeDetailsQueue().add(handler.getCurrentMovieList().get(0));
                }
            }

            // handle the 'see details' case
            if (nextPage.getName().equals(SEE_DETAILS_PAGE)) {
                GetMovie.seeDetails(handler, output, objectMapper, nextPage, back);
                return;
            }

            handler.setCurrentPage(nextPage);

            String currentPageName = handler.getCurrentPage().getName();
            // handle the 'logout' case
            if (currentPageName.equals(LOGOUT_PAGE)) {
                // reset current user and current movie list and changes page to unlogged homepage
                handler.setCurrentUser(null);
                handler.setCurrentMovieList(new ArrayList<>());
                handler.setPreviousPages(new ArrayList<>());
                handler.setCurrentPage(mainDatabase.getPageMap().get(HOMEPAGE_UNLOGGED));
                return;
            }

            // if next page is 'Movies', a movie list should be changed inside handler
            if (currentPageName.equals(MOVIES_PAGE)) {
                GetMovie.getMovies(mainDatabase, handler);

                output.add(OutputHandler.outputHandler(handler, false, objectMapper));
                return;
            }

            if (!handler.getCurrentMovieList().isEmpty()) {
                handler.setCurrentMovieList(new ArrayList<>());
            }

            return;
        }

        output.add(OutputHandler.outputHandler(handler, true, objectMapper));
    }
}
