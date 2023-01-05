package user;

import action.Action;

import movie.Movie;

import page.Page;

import java.util.ArrayList;

/**
 * @class class for handler data
 * @details contains the current user doing actions, the current page that the user is on, the
 * current action that the user does and the current movie list that the user sees on a page
 * */
public class Handler {
    private User currentUser; /** current user */
    private Page currentPage; /** current page that the user is on */
    private Action currentAction; /** current action that the user does */
    private ArrayList<Movie> currentMovieList; /** current movie list that the user sees */
    private ArrayList<String> previousPages = new ArrayList<>(); /** previous accessed pages */
    private ArrayList<Movie> seeDetailsQueue = new ArrayList<>(); /** memorises the previous see
     details movies in case 'back' action is called */

    /** Setters */
    public final void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }
    public final void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }
    public final void setCurrentAction(final Action currentAction) {
        this.currentAction = currentAction;
    }
    public final void setCurrentMovieList(final ArrayList<Movie> currentMovieList) {
        this.currentMovieList = currentMovieList;
    }
    public void setPreviousPages(ArrayList<String> previousPages) {
        this.previousPages = previousPages;
    }
    public void setSeeDetailsQueue(ArrayList<Movie> seeDetailsQueue) {
        this.seeDetailsQueue = seeDetailsQueue;
    }

    /** Getters */
    public final User getCurrentUser() {
        return currentUser;
    }
    public final Page getCurrentPage() {
        return currentPage;
    }
    public final Action getCurrentAction() {
        return currentAction;
    }
    public final ArrayList<Movie> getCurrentMovieList() {
        return currentMovieList;
    }
    public ArrayList<String> getPreviousPages() {
        return previousPages;
    }
    public ArrayList<Movie> getSeeDetailsQueue() {
        return seeDetailsQueue;
    }

    public Handler(final User currentUser, final Page currentPage, final Action currentAction,
                   final ArrayList<Movie> currentMovieList) {
        this.currentUser = currentUser;
        this.currentPage = currentPage;
        this.currentAction = currentAction;
        this.currentMovieList = currentMovieList;
    }
}
