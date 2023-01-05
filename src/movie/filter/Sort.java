package movie.filter;

import movie.Movie;

import user.Handler;

import java.util.ArrayList;
import java.util.Comparator;

import static database.Constant.DECREASING_SORT;
import static database.Constant.INCREASING_SORT;

/**
 * @class class for sorting
 * @details sorts the current movie list by either duration or rating. If both are selected, sorts
 * by duration first.
 * */

public final class Sort {

    /**
     * @param  handler -> handler for the current user
     * @param newMovieList -> current movie list to be sorted
     * @details sorts a movie list by duration first, then by rating (if selected)
     * */
    public static void sortByDuration(final Handler handler, final ArrayList<Movie> newMovieList) {
        Movie aux;
        MovieSort sortType = handler.getCurrentAction().getFilters().getSort();

        // sorting by selection (can't use collection sort if both sorting types are selected)
        for (int i = 0; i < newMovieList.size() - 1; i++) {
            for (int j = i + 1; j < newMovieList.size(); j++) {

                // sorting by duration first
                /* if i movie duration lesser than j movie duration and duration sort is
                'decreasing', makes the swap */
                if (newMovieList.get(i).getDuration() < newMovieList.get(j).getDuration()) {

                    if (sortType.getDuration().equals(DECREASING_SORT)) {
                        aux = newMovieList.get(i);
                        newMovieList.set(i, newMovieList.get(j));
                        newMovieList.set(j, aux);
                    }

                /* if i movie duration greater than j movie duration and duration sort is
                'increasing', makes the swap */
                } else if (newMovieList.get(i).getDuration() > newMovieList.get(j).getDuration()) {

                    if (sortType.getDuration().equals(INCREASING_SORT)) {
                        aux = newMovieList.get(i);
                        newMovieList.set(i, newMovieList.get(j));
                        newMovieList.set(j, aux);
                    }

                /* if i movie duration equal with j movie duration and rating sort exists, then
                sorts the two movies by rating */
                } else {

                    /* if i movie rating lesser than j movie duration and rating sort is
                    'decreasing', makes the swap */
                    if (newMovieList.get(i).getRating() < newMovieList.get(j).getRating()) {

                        if (sortType.getRating() != null) {
                            if (sortType.getRating().equals(DECREASING_SORT)) {
                                aux = newMovieList.get(i);
                                newMovieList.set(i, newMovieList.get(j));
                                newMovieList.set(j, aux);
                            }
                        }

                    /* if i movie rating greater than j movie duration and rating sort is
                    'increasing', makes the swap */
                    } else if (newMovieList.get(i).getRating() > newMovieList.get(j).getRating()) {

                        if (sortType.getRating() != null) {
                            if (sortType.getRating().equals(INCREASING_SORT)) {
                                aux = newMovieList.get(i);
                                newMovieList.set(i, newMovieList.get(j));
                                newMovieList.set(j, aux);
                            }
                        }
                    }

                }
            }
        }
    }

    /**
     * @param  handler -> handler for the current user
     * @param newMovieList -> current movie list to be sorted
     * @details sorts a movie list by rating
     * */
    public static void sortByRating(final Handler handler, final ArrayList<Movie> newMovieList) {
        MovieSort sortType = handler.getCurrentAction().getFilters().getSort();

        // using collection sort here
        if (sortType.getRating().equals(INCREASING_SORT)) {
            newMovieList.sort(Comparator.comparingDouble(Movie::getRating));
        } else {
            newMovieList.sort(Comparator.comparingDouble(Movie::getRating).reversed());
        }
    }
}
