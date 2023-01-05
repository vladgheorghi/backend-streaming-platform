package movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import output.OutputHandler;
import page.Page;
import user.Handler;

import java.util.ArrayList;

import static database.Constant.SEE_DETAILS_PAGE;

/**
 * @class class for returning movie list
 * @details returns a movie list when called based on the page that the user is on
 * */

public final class GetMovie {

    /**
     * @param mainDatabase -> main database with all program data (used to get the movie list)
     * @param handler -> handler for the current user
     * @details stores in handlers 'currentMovieList' the movies stored in the database that
     * the current user can see
     * */
    public static void getMovies(final Database mainDatabase, final Handler handler) {
        ArrayList<Movie> newMovieList = new ArrayList<>();

        // goes through each movie inside the database movie list
        for (Movie movie : mainDatabase.getMovies()) {
            String userCountry = handler.getCurrentUser().getCredentials().getCountry();

            /* if the movie is not banned in the current user's country, adds it
            to the new movie list */
            if (!movie.getCountriesBanned().contains(userCountry)) {
                newMovieList.add(movie);
            }
        }

        // the new 'currentMovieList' in the handler is the created list
        handler.setCurrentMovieList(newMovieList);
    }

    /**
     * @param handler -> handler for the current user
     * @param output -> outputs JSON objects in the output file in case of error
     * @param objectMapper -> the object mapper used to create JSON objects and arrays
     * @param nextPage -> contains the 'see details' page and changes the current page to it if
     *                 the movie's details can't be accessed
     * @details changes page to the 'see details' page and changes handler's 'currentMovieList' to
     * contain only the movie with the details
     * */
    public static void seeDetails(final Handler handler, final ArrayNode output,
                                  final ObjectMapper objectMapper, final Page nextPage,
                                  boolean back) {
        ArrayList<Movie> newMovieList = new ArrayList<>();

        if (back) {
            int seeDetailsQueueSize = handler.getSeeDetailsQueue().size();
            String movieName = handler.getSeeDetailsQueue().get(seeDetailsQueueSize - 1).getName();

            handler.getCurrentAction().setMovie(movieName);
        }

        // searches for the desired movie in the handler's 'currentMovieList'
        for (Movie movie : handler.getCurrentMovieList()) {
            if (movie.getName().equals(handler.getCurrentAction().getMovie())) {
                String userCountry = handler.getCurrentUser().getCredentials().getCountry();

                // adds the movie in the new list if it is not banned in the current user's country
                if (!movie.getCountriesBanned().contains(userCountry)) {
                    newMovieList.add(movie);
                    break;
                } else {
                    // if the movie is banned in the current user's country, outputs an error
                    handler.setCurrentMovieList(newMovieList);

                    /* also removes 'movies' from previous visited pages list because current page
                       is still 'movies' */
                    handler.getPreviousPages().remove(handler.getPreviousPages().size() - 1);

                    output.add(OutputHandler.outputHandler(handler, true, objectMapper));
                    return;
                }
            }
        }

        // if the movie is not found, outputs an error
        if (newMovieList.isEmpty()) {
            /* also removes 'movies' from previous visited pages list because current page
               is still 'movies' */
            handler.getPreviousPages().remove(handler.getPreviousPages().size() - 1);

            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        /* changes the handler's 'currentMovieList' with the new list and changes page to
        'see details' */
        handler.setCurrentMovieList(newMovieList);
        handler.setCurrentPage(nextPage);

        output.add(OutputHandler.outputHandler(handler, false, objectMapper));
    }
}
