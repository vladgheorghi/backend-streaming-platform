package movie;

import user.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * @class class for movie data
 * @details contains movie data parsed by the JSON input file
 * */

public class Movie {
    private String name; /** name of the movie */
    private int year; /** year in which the movie was published */
    private int duration; /** duration of the movie */
    private ArrayList<String> genres; /** list of genres that the movie contains */
    private ArrayList<String> actors; /** list with the actors that play in the movie */
    private ArrayList<String> countriesBanned; /** list with countries the movie is banned in */
    private int numLikes; /** number of likes */
    private double rating; /** current movie rating (calculated at each rating */
    private int numRatings; /** number of ratings */
    private ArrayList<Integer> ratingList; /** past ratings given to the movie */

    /** Setters */
    public final void setName(final String name) {
        this.name = name;
    }

    public final void setYear(final int year) {
        this.year = year;
    }

    public final void setDuration(final int duration) {
        this.duration = duration;
    }

    public final void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public final void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public final void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public final void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public final void setRating(final double rating) {
        this.rating = rating;
    }

    public final void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    public final void setRatingList(final ArrayList<Integer> ratingList) {
        this.ratingList = ratingList;
    }

    /** Getters */
    public final String getName() {
        return name;
    }

    public final int getYear() {
        return year;
    }

    public final int getDuration() {
        return duration;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }

    public final ArrayList<String> getActors() {
        return actors;
    }

    public final ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public final int getNumLikes() {
        return numLikes;
    }

    public final double getRating() {
        return rating;
    }

    public final int getNumRatings() {
        return numRatings;
    }

    public final ArrayList<Integer> getRatingList() {
        return ratingList;
    }
}
