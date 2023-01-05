package action;

import movie.Movie;
import movie.filter.Filter;
import user.Credentials;

/**
 * @class class for action data
 * @details JSON input file parses action data into these fields. Used as an ArrayList inside the
 * Database class
 */
public class Action {
    private String type; /** action type "on page" or "change page" */
    private String page; /** if type = "change page" specified, contains the next page */
    private String feature; /** access a feature in the given page (e.g. "register", "login") */
    private String movie; /** if type = "change page" and page = "see details", has movie title */
    private Credentials credentials; /** exists if feature = "register" or "login" (user info) */
    private String startsWith; /** exists if  feature = "search" (movies that start with this) */
    private Filter filters; /** exists if feature = "filter" (show movies based on criteria) */
    private String count; /** exists if feature = "buy tokens" (currency for app transactions) */
    private int rate; /** exists if feature = "rate" (rate a movie 1-5) */
    private String subscribedGenre; /** exists if type = "subscribe" and page is "See details" */
    private Movie addedMovie; /** exists if a movie has to be added in the database */
    private String deletedMovie; /** exists if a movie has to be deleted from the database */

    /** Action getters */
    public final String getType() {
        return type;
    }
    public final String getPage() {
        return page;
    }
    public final String getFeature() {
        return feature;
    }
    public final String getMovie() {
        return movie;
    }
    public final Credentials getCredentials() {
        return credentials;
    }
    public final String getStartsWith() {
        return startsWith;
    }
    public final Filter getFilters() {
        return filters;
    }
    public final String getCount() {
        return count;
    }
    public final int getRate() {
        return rate;
    }
    public final String getSubscribedGenre() {
        return subscribedGenre;
    }
    public final Movie getAddedMovie() {
        return addedMovie;
    }
    public final String getDeletedMovie() {
        return deletedMovie;
    }

    /** Action setters */
    public final void setType(final String type) {
        this.type = type;
    }
    public final void setPage(final String page) {
        this.page = page;
    }
    public final void setFeature(final String feature) {
        this.feature = feature;
    }
    public final void setMovie(final String movie) {
        this.movie = movie;
    }
    public final void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }
    public final void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }
    public final void setFilters(final Filter filters) {
        this.filters = filters;
    }
    public final void setCount(final String count) {
        this.count = count;
    }
    public final void setRate(final int rate) {
        this.rate = rate;
    }
    public final void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }
    public final void setAddedMovie(final Movie addedMovie) {
        this.addedMovie = addedMovie;
    }
    public final void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }
}
