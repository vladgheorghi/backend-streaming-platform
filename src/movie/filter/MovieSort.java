package movie.filter;

/**
 * @class class for 'sort' filter
 * @details stores filters used in the 'filter' action. Sorts the current movie list by the
 * specified criteria (duration or rating). If both are selected, sorts by duration first. Sorting
 * can be 'increasing' or 'decreasing'
 * */

public class MovieSort {
    private String rating; /** sort by rating (increasing or decreasing) */
    private String duration; /** sort by duration (increasing or decreasing) */

    /** Getters */
    public final String getRating() {
        return rating;
    }

    public final String getDuration() {
        return duration;
    }

    /** Setters */
    public final void setRating(final String rating) {
        this.rating = rating;
    }

    public final void setDuration(final String duration) {
        this.duration = duration;
    }
}
