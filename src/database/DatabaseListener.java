package database;

import movie.Movie;

/**
 * @class class for notifying users about a modification to the database
 * @details update() declaration is overridden by concrete implementations (notifications about
 * addition, removal)
 * */

public interface DatabaseListener {
    /**
     * @param movie -> movie that users will get notified about (addition or removal)
     *                 (if they subscribed to a genre from that movie)
     * @param mainDatabase -> the main database with all data
     * @details notifies the users about a modification to the database
     * */
    void update(Movie movie, Database mainDatabase);
}
