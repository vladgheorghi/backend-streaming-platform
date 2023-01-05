package user;

import movie.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static database.Constant.FREE_PREMIUM_MOVIES;

/**
 * @class class for used data
 * @details contains all user data. Parsed by the JSON input file
 * */
public class User {
    private Credentials credentials; /** user credentials (e.g. name, password) */
    private int tokensCount = 0; /** number of tokens that the user has */
    private int numFreePremiumMovies = FREE_PREMIUM_MOVIES; /** number of free movies the user can
                                                            have if he has a premium account*/
    private ArrayList<String> subscribedGenres = new ArrayList<>();
    private ArrayList<Movie> purchasedMovies = new ArrayList<>(); /** user purchased movie list */
    private ArrayList<Movie> watchedMovies = new ArrayList<>(); /** user watched movie list */
    private ArrayList<Movie> likedMovies = new ArrayList<>(); /** user liked movie list */
    private ArrayList<Movie> ratedMovies = new ArrayList<>(); /** user rated movie list */
    private ArrayList<Notification> notifications = new ArrayList<>(); /** user notifications */
    private Map<String, Integer> ratingList = new HashMap<>(); /** user rated movies */

    /** Getters */
    public final Credentials getCredentials() {
        return credentials;
    }
    public final int getTokensCount() {
        return tokensCount;
    }
    public final int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }
    public final ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }
    public final ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }
    public final ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }
    public final ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }
    public final ArrayList<Notification> getNotifications() {
        return notifications;
    }
    public final ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }
    public final Map<String, Integer> getRatingList() {
        return ratingList;
    }

    /** Setters */
    public final void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }
    public final void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }
    public final void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }
    public final void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }
    public final void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }
    public final void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }
    public final void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
    public final void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }
    public final void setSubscribedGenres(final ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }
    public final void setRatingList(final Map<String, Integer> ratingList) {
        this.ratingList = ratingList;
    }
}
