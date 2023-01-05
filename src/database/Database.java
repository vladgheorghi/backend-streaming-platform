package database;

import action.Action;
import movie.Movie;
import page.Page;
import user.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * @class class for the main database
 * @details the JSON input file parses all data here (user, movie, action data). Also contains the
 * page hierarchy as a map for easier access
 * */

public final class Database {
    private ArrayList<User> users; /** user data */
    private ArrayList<Movie> movies; /** movie data */
    private ArrayList<Action> actions; /** action data */
    private Map<String, Page> pageMap; /** page hierarchy */

    /** Getters */
    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public Map<String, Page> getPageMap() {
        return pageMap;
    }

    /** Setters */
    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }

    public void setPageMap(final Map<String, Page> pageMap) {
        this.pageMap = pageMap;
    }
}
