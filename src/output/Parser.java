package output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import movie.Movie;

import user.Notification;
import user.User;

/**
 * @class class for parsing
 * @details parses different data types as asked
 * */

public class Parser {
    /**
     * @param movie -> movie to be parsed
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @return ObjectNode -> returns an object with the parsed movie
     * */
    public static ObjectNode parseMovie(final Movie movie, final ObjectMapper objectMapper) {
        ObjectNode movieNode = objectMapper.createObjectNode();

        movieNode.put("name", movie.getName());
        movieNode.put("year", Integer.toString(movie.getYear()));
        movieNode.put("duration", movie.getDuration());

        ArrayNode movieGenres = objectMapper.createArrayNode();
        for (String genre : movie.getGenres()) {
            movieGenres.add(genre);
        }
        movieNode.set("genres", movieGenres);

        ArrayNode movieActors = objectMapper.createArrayNode();
        for (String actor : movie.getActors()) {
            movieActors.add(actor);
        }
        movieNode.set("actors", movieActors);

        ArrayNode movieCountriesBanned = objectMapper.createArrayNode();
        for (String country : movie.getCountriesBanned()) {
            movieCountriesBanned.add(country);
        }
        movieNode.set("countriesBanned", movieCountriesBanned);

        movieNode.put("numLikes", movie.getNumLikes());
        movieNode.put("rating", movie.getRating());
        movieNode.put("numRatings", movie.getNumRatings());

        return movieNode;
    }

    /**
     * @param user -> user to be parsed
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @return ObjectNode -> returns an object with the parsed user
     * */
    public static ObjectNode parseUser(final User user, final ObjectMapper objectMapper) {
        ObjectNode userNode = objectMapper.createObjectNode();

        ObjectNode credentialsNode = objectMapper.createObjectNode();

        credentialsNode.put("name", user.getCredentials().getName());
        credentialsNode.put("password", user.getCredentials().getPassword());
        credentialsNode.put("accountType", user.getCredentials().getAccountType());
        credentialsNode.put("country", user.getCredentials().getCountry());
        credentialsNode.put("balance", user.getCredentials().getBalance());

        userNode.set("credentials", credentialsNode);
        userNode.put("tokensCount", user.getTokensCount());
        userNode.put("numFreePremiumMovies", user.getNumFreePremiumMovies());

        ArrayNode purchasedMoviesNode = objectMapper.createArrayNode();
        for (Movie movie : user.getPurchasedMovies()) {
            purchasedMoviesNode.add(parseMovie(movie, objectMapper));
        }
        userNode.set("purchasedMovies", purchasedMoviesNode);

        ArrayNode watchedMoviesNode = objectMapper.createArrayNode();
        for (Movie movie : user.getWatchedMovies()) {
            watchedMoviesNode.add(parseMovie(movie, objectMapper));
        }
        userNode.set("watchedMovies", watchedMoviesNode);

        ArrayNode likedMoviesNode = objectMapper.createArrayNode();
        for (Movie movie : user.getLikedMovies()) {
            likedMoviesNode.add(parseMovie(movie, objectMapper));
        }
        userNode.set("likedMovies", likedMoviesNode);

        ArrayNode ratedMoviesNode = objectMapper.createArrayNode();
        for (Movie movie : user.getRatedMovies()) {
            ratedMoviesNode.add(parseMovie(movie, objectMapper));
        }
        userNode.set("ratedMovies", ratedMoviesNode);

        ArrayNode notificationsNode = objectMapper.createArrayNode();
        for (Notification notification : user.getNotifications()) {
            ObjectNode notificationNode = objectMapper.createObjectNode();
            notificationNode.put("movieName", notification.getMovieName());
            notificationNode.put("message", notification.getMessage());

            notificationsNode.add(notificationNode);
        }
        userNode.set("notifications", notificationsNode);

        return userNode;
    }
}
