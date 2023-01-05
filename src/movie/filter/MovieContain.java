package movie.filter;

import java.util.ArrayList;

/**
 * @class class for 'contains' filter
 * @details stores filters used in the 'filter' action that ask a movie to have a list of actors
 * or a list of genres or both. This filters the current movie list seen by the user
 * */

public class MovieContain {
    private ArrayList<String> actors; /** list of actors that should be in the movie */
    private ArrayList<String> genre; /** list of genres that should be in the movie */

    /** Getters */
    public final ArrayList<String> getActors() {
        return actors;
    }

    public final ArrayList<String> getGenre() {
        return genre;
    }

    /** Setters */
    public final void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public final void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }
}
