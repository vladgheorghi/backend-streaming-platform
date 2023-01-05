package movie.filter;

import movie.Movie;
import user.Handler;

/**
 * @class class for verifying filters types for the 'filter' action
 * @details verifies if a certain movie contains the desired actors or genres the movie should be
 * filtered on
 * */

public final class ConfirmFilter {
    /**
     * @param handler -> handler for the current user
     * @param movie -> movie that should contain the actors
     * @return true if the movie contains the desired actors
     * @return false if the movie does not contain the desired actors
     * */
    public static boolean containActors(final Handler handler, final Movie movie) {
        // goes through the actors of the filter and verifies if they are in the movie
        for (String actor : handler.getCurrentAction().getFilters().getContains().getActors()) {
            if (!movie.getActors().contains(actor)) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param handler -> handler for the current user
     * @param movie -> movie that should contain the genres
     * @return true if the movie contains the desired genres
     * @return false if the movie does not contain the desired genres
     * */
    public static boolean containGenders(final Handler handler, final Movie movie) {
        // goes through the genres of the filter and verifies if the movie is in those genres
        for (String genre : handler.getCurrentAction().getFilters().getContains().getGenre()) {
            if (!movie.getGenres().contains(genre)) {
                return false;
            }
        }

        return true;
    }
}
