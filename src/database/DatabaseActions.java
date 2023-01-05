package database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import movie.Movie;
import output.OutputHandler;
import user.Handler;
import user.Notification;
import user.User;

import javax.xml.crypto.Data;

import java.util.ArrayList;

import static database.Constant.*;

public class DatabaseActions {
    public static void databaseAction(Database mainDatabase, Handler handler, ArrayNode output,
                                      ObjectMapper objectMapper) {
        if (handler.getCurrentAction().getFeature().equals(ADD_MOVIE)) {
            addMovie(mainDatabase, handler, output, objectMapper);
        } else if (handler.getCurrentAction().getFeature().equals(DELETE_MOVIE)) {
            deleteMovie(mainDatabase, handler, output, objectMapper);
        } else {
            // outputs error in JSON file if database feature is not recognised
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
        }
    }

    public static void addMovie(Database mainDatabase, Handler handler, ArrayNode output,
                                ObjectMapper objectMapper) {
        Movie newMovie = handler.getCurrentAction().getAddedMovie();

        for (Movie movie : mainDatabase.getMovies()) {
            if (movie.getName().equals(newMovie.getName())) {
                // outputs error in JSON file if movie already is in the database
                output.add(OutputHandler.outputHandler(handler, true, objectMapper));
                return;
            }
        }

        mainDatabase.getMovies().add(newMovie);

        Notification newNotification = new Notification(newMovie.getName(), ADDED_MOVIE);

        for (String genre : newMovie.getGenres()) {
            for (User user : mainDatabase.getUsers()) {
                if (!newMovie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                    if (user.getSubscribedGenres().contains(genre)) {
                        user.getNotifications().add(newNotification);
                    }
                }
            }
        }
    }

    public static void deleteMovie(Database mainDatabase, Handler handler, ArrayNode output,
                                   ObjectMapper objectMapper) {
        String deletedMovie = handler.getCurrentAction().getDeletedMovie();
        boolean movieExists = false;

        for (Movie movie : mainDatabase.getMovies()) {
            if (movie.getName().equals(deletedMovie)) {
                mainDatabase.getMovies().remove(movie);
                movieExists = true;
                break;
            }
        }

        if (!movieExists) {
            // outputs error in JSON file if movie is not in the database
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
        }

        Notification newNotification = new Notification(deletedMovie, DELETED_MOVIE);

        for (User user : mainDatabase.getUsers()) {
            for (Movie movie : user.getPurchasedMovies()) {
                if (movie.getName().equals(deletedMovie)) {
                    user.getPurchasedMovies().remove(movie);
                    user.getNotifications().add(newNotification);
                    break;
                }
            }

            for (Movie movie : user.getLikedMovies()) {
                if (movie.getName().equals(deletedMovie)) {
                    user.getLikedMovies().remove(movie);
                    break;
                }
            }

            for (Movie movie : user.getWatchedMovies()) {
                if (movie.getName().equals(deletedMovie)) {
                    user.getWatchedMovies().remove(movie);
                    break;
                }
            }

            for (Movie movie : user.getRatedMovies()) {
                if (movie.getName().equals(deletedMovie)) {
                    user.getRatedMovies().remove(movie);
                    break;
                }
            }
        }
    }
}
