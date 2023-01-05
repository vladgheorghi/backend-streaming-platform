package database;

import movie.Movie;
import user.Notification;
import user.User;

import static database.Constant.ADDED_MOVIE;

/**
 * @class class for notifying users for the addition of a new movie to the database
 * @details update() function overrides the function from the interface
 * */

public class AddMovieListener implements DatabaseListener{
    /**
     * @param newMovie -> movie added to the database that users will get notified about
     *                 (if they subscribed to a genre from that movie)
     * @param mainDatabase -> the main database with all data
     * @details notifies the users about the addition of a new movie to the database
     * */
    @Override
    public void update(Movie newMovie, Database mainDatabase) {
        DatabaseNotificationsService addNotifications = new DatabaseNotificationsService();

        Notification newNotification = new Notification(newMovie.getName(), ADDED_MOVIE);

        for (String genre : newMovie.getGenres()) {
            for (User user : mainDatabase.getUsers()) {
                if (!newMovie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                    if (user.getSubscribedGenres().contains(genre)
                            && !user.getNotifications().contains(newNotification)) {
                        addNotifications.notifyUser(user, newNotification);
                    }
                }
            }
        }
    }
}
