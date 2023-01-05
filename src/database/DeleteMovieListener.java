package database;

import movie.Movie;
import user.Notification;
import user.User;

import static database.Constant.DELETED_MOVIE;

/**
 * @class class for notifying users for the removal of a new movie to the database
 * @details update() function overrides the function from the interface
 * */
public class DeleteMovieListener implements DatabaseListener {
    /**
     * @param deletedMovie -> movie removed from the database that users will get notified about
     *                 (if they subscribed to a genre from that movie)
     * @param mainDatabase -> the main database with all data
     * @details notifies the users about the removal of a new movie to the database
     * */
    @Override
    public void update(final Movie deletedMovie, final Database mainDatabase) {
        DatabaseNotificationsService addNotifications = new DatabaseNotificationsService();

        Notification newNotification = new Notification(deletedMovie.getName(), DELETED_MOVIE);

        for (User user : mainDatabase.getUsers()) {
            for (Movie movie : user.getPurchasedMovies()) {
                if (movie.getName().equals(deletedMovie.getName())) {
                    addNotifications.notifyUser(user, newNotification);
                    break;
                }
            }
        }
    }
}
