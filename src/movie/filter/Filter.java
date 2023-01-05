package movie.filter;

/**
 * @class class for filters
 * @details stores filters used in the 'filter' action. The filter can ask a movie to contain
 * specific actors or genres and can be sorted either by duration, rating or both. Used inside the
 * 'Movies' page. Filter removed when changing page
 * */

public class Filter {
    private MovieSort sort; /** sorting type (by duration or by rating) */
    private MovieContain contains; /** filter for actors or genres in a movie */

    /** Getters */
    public final MovieSort getSort() {
        return sort;
    }

    public final MovieContain getContains() {
        return contains;
    }

    /** Setters */
    public final void setSort(final MovieSort sort) {
        this.sort = sort;
    }

    public final void setContains(final MovieContain contains) {
        this.contains = contains;
    }
}
