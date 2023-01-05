package database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import movie.Movie;
import output.OutputHandler;
import user.Handler;
import user.User;

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

        AddMovieListener notifyUsers = new AddMovieListener();

        notifyUsers.update(newMovie, mainDatabase);
    }

    public static void deleteMovie(Database mainDatabase, Handler handler, ArrayNode output,
                                   ObjectMapper objectMapper) {
        String deletedMovieName = handler.getCurrentAction().getDeletedMovie();
        Movie deletedMovie = null;

        for (Movie movie : mainDatabase.getMovies()) {
            if (movie.getName().equals(deletedMovieName)) {
                deletedMovie = movie;
                mainDatabase.getMovies().remove(movie);
                break;
            }
        }

        if (deletedMovie == null) {
            // outputs error in JSON file if movie is not in the database
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        DatabaseListener notifyUsers = new DeleteMovieListener();

        notifyUsers.update(deletedMovie, mainDatabase);

        for (User user : mainDatabase.getUsers()) {
            for (Movie movie : user.getPurchasedMovies()) {
                if (movie.getName().equals(deletedMovieName)) {
                    user.getPurchasedMovies().remove(movie);
                    break;
                }
            }

            for (Movie movie : user.getLikedMovies()) {
                if (movie.getName().equals(deletedMovieName)) {
                    user.getLikedMovies().remove(movie);
                    break;
                }
            }

            for (Movie movie : user.getWatchedMovies()) {
                if (movie.getName().equals(deletedMovieName)) {
                    user.getWatchedMovies().remove(movie);
                    break;
                }
            }

            for (Movie movie : user.getRatedMovies()) {
                if (movie.getName().equals(deletedMovieName)) {
                    user.getRatedMovies().remove(movie);
                    break;
                }
            }
        }
    }
}
