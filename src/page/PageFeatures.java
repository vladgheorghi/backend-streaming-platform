package page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import database.Database;

import movie.GetMovie;
import movie.Movie;
import movie.filter.ConfirmFilter;
import movie.filter.MovieSort;
import movie.filter.Sort;

import output.OutputHandler;

import user.Handler;
import user.User;

import java.util.*;

import static database.Constant.HOMEPAGE_LOGGED;
import static database.Constant.HOMEPAGE_UNLOGGED;
import static database.Constant.ACCOUNT_STANDARD;
import static database.Constant.ACCOUNT_PREMIUM;
import static database.Constant.MOVIE_PRICE;
import static database.Constant.PREMIUM_ACCOUNT_PRICE;
import static database.Constant.MAX_RATE;
import static database.Constant.MIN_RATE;

/**
 * @class class for all page features
 * @details contains all page feature methods that are called inside the current page
 * */

public final class PageFeatures {
    /**
     * @param mainDatabase -> main database with all data
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details logs a user into the platform
     * */
    public static void login(final Database mainDatabase, final Handler handler,
                             final ArrayNode output, final ObjectMapper objectMapper) {
        // goes through each user
        for (User user : mainDatabase.getUsers()) {
            String userName = user.getCredentials().getName();
            String password = user.getCredentials().getPassword();

            // verifies credentials with each user (username and password)
            if (userName.equals(handler.getCurrentAction().getCredentials().getName())) {
                if (password.equals(handler.getCurrentAction().getCredentials().getPassword())) {
                    // user has been found and now can access the homepage logged page
                    handler.setCurrentUser(user);
                    handler.setCurrentPage(mainDatabase.getPageMap().get(HOMEPAGE_LOGGED));

                    output.add(OutputHandler.outputHandler(handler, false, objectMapper));

                    return;
                }
            }
        }

        // login failed (user inside the database was not found), so outputs error
        output.add(OutputHandler.outputHandler(handler, true, objectMapper));
        handler.setCurrentPage(mainDatabase.getPageMap().get(HOMEPAGE_UNLOGGED));
    }

    /**
     * @param mainDatabase -> main database with all data
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details registers a user into the platform
     * */
    public static void register(final Database mainDatabase, final Handler handler,
                                final ArrayNode output, final ObjectMapper objectMapper) {
        // goes through each user in the database
        for (User user : mainDatabase.getUsers()) {
            String userName = user.getCredentials().getName();
            String password = user.getCredentials().getPassword();

            // if the user is already in the database, outputs error
            if (userName.equals(handler.getCurrentAction().getCredentials().getName())) {
                if (password.equals(handler.getCurrentAction().getCredentials().getPassword())) {
                    handler.setCurrentPage(mainDatabase.getPageMap().get(HOMEPAGE_UNLOGGED));

                    output.add(OutputHandler.outputHandler(handler, true, objectMapper));

                    return;
                }
            }
        }

        // creates new user and logs in
        User newUser = new User();
        newUser.setCredentials(handler.getCurrentAction().getCredentials());

        mainDatabase.getUsers().add(newUser);

        handler.setCurrentUser(newUser);
        handler.setCurrentPage(mainDatabase.getPageMap().get(HOMEPAGE_LOGGED));

        output.add(OutputHandler.outputHandler(handler, false, objectMapper));
    }

    /**
     * @param mainDatabase -> main database with all data
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details searches for movies that start with a certain String inside the database
     * */
    public static void search(final Database mainDatabase, final Handler handler,
                              final ArrayNode output, final ObjectMapper objectMapper) {
        GetMovie.getMovies(mainDatabase, handler);

        ArrayList<Movie> newMovieList = new ArrayList<>();

        // search all the movies that start with a certain String and creates new list with them
        for (Movie movie : handler.getCurrentMovieList()) {
            if (movie.getName().startsWith(handler.getCurrentAction().getStartsWith())) {
                newMovieList.add(movie);
            }
        }

        handler.setCurrentMovieList(newMovieList);

        output.add(OutputHandler.outputHandler(handler, false, objectMapper));
    }

    /**
     * @param mainDatabase -> main database with all data
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details filters the current movie list based on a certain criteria
     * */
    public static void filter(final Database mainDatabase, final Handler handler,
                              final ArrayNode output, final ObjectMapper objectMapper) {
        GetMovie.getMovies(mainDatabase, handler);

        // gets the current movie list and removes movies that do not correspond with the filter
        ArrayList<Movie> newMovieList = handler.getCurrentMovieList();

        // verifies if a filter is specified
        if (handler.getCurrentAction().getFilters().getContains() != null) {
            // filters by actors if specified
            if (handler.getCurrentAction().getFilters().getContains().getActors() != null) {
                for (int i = 0; i < newMovieList.size(); i++) {
                    if (!ConfirmFilter.containActors(handler, newMovieList.get(i))) {
                        newMovieList.remove(i);
                        i--;
                    }
                }

                // if the list is empty, no reason to continue with filtering
                if (newMovieList.isEmpty()) {
                    handler.setCurrentMovieList(newMovieList);
                    output.add(OutputHandler.outputHandler(handler, false, objectMapper));
                    return;
                }
            }

            // filters by genre if specified
            if (handler.getCurrentAction().getFilters().getContains().getGenre() != null) {
                for (int i = 0; i < newMovieList.size(); i++) {
                    if (!ConfirmFilter.containGenders(handler, newMovieList.get(i))) {
                        newMovieList.remove(i);
                        i--;
                    }
                }

                if (newMovieList.isEmpty()) {
                    handler.setCurrentMovieList(newMovieList);
                    output.add(OutputHandler.outputHandler(handler, false, objectMapper));
                    return;
                }
            }
        }

        // verifies if any sorting is specified
        if (handler.getCurrentAction().getFilters().getSort() != null) {
            MovieSort sortType = handler.getCurrentAction().getFilters().getSort();

            // sort by duration if specified
            if (sortType.getDuration() != null) {
                Sort.sortByDuration(handler, newMovieList);
            // sort by rating if specified
            } else if (sortType.getRating() != null) {
                Sort.sortByRating(handler, newMovieList);
            }
        }

        // handler's 'currentMovieList' becomes the filtered or sorted list
        handler.setCurrentMovieList(newMovieList);

        output.add(OutputHandler.outputHandler(handler, false, objectMapper));
    }

    /**
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details purchases a movie (only on 'see details' page)
     * */
    public static void purchase(final Handler handler, final ArrayNode output,
                                final ObjectMapper objectMapper) {

        int userTokens = handler.getCurrentUser().getTokensCount();
        // gets the 'see details' movie
        Movie currentMovie = handler.getCurrentMovieList().get(0);

        // if movie was already purchased, outputs error
        if (handler.getCurrentUser().getPurchasedMovies().contains(currentMovie)) {
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        // if the account is standard, the movie is purchased using the user's tokens
        if (handler.getCurrentUser().getCredentials().getAccountType().equals(ACCOUNT_STANDARD)) {

            if (userTokens - MOVIE_PRICE < 0) {
                output.add(OutputHandler.outputHandler(handler, true, objectMapper));
                return;
            }

            handler.getCurrentUser().getPurchasedMovies().add(currentMovie);

            handler.getCurrentUser().setTokensCount(userTokens - MOVIE_PRICE);
            output.add(OutputHandler.outputHandler(handler, false, objectMapper));
            return;
        }

        // if the user is premium, the movie is purchased using the free movies given
        // if there is no free premium movie left, purchase is made by user's tokens
        int numFreePremiumMovies = handler.getCurrentUser().getNumFreePremiumMovies();

        if (numFreePremiumMovies <= 0) {
            if (userTokens - MOVIE_PRICE < 0) {
                output.add(OutputHandler.outputHandler(handler, true, objectMapper));
                return;
            }

            handler.getCurrentUser().setTokensCount(userTokens - MOVIE_PRICE);
        } else {
            handler.getCurrentUser().setNumFreePremiumMovies(numFreePremiumMovies - 1);
        }

        handler.getCurrentUser().getPurchasedMovies().add(currentMovie);
        output.add(OutputHandler.outputHandler(handler, false, objectMapper));
    }

    /**
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details watches a movie (only on 'see details' page and if the movie was purchased)
     * */
    public static void watch(final Handler handler, final ArrayNode output,
                             final ObjectMapper objectMapper) {
        Movie currentMovie = handler.getCurrentMovieList().get(0);

        // if the movie was not purchased, outputs error
        if (!handler.getCurrentUser().getPurchasedMovies().contains(currentMovie)) {
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        // adds the movie to the 'watchedMovies' list if not already watched
        if (!handler.getCurrentUser().getWatchedMovies().contains(currentMovie)) {
            handler.getCurrentUser().getWatchedMovies().add(currentMovie);
            output.add(OutputHandler.outputHandler(handler, false, objectMapper));
        }
    }

    /**
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details likes a movie (only on 'see details' page and if the movie was watched)
     * */
    public static void like(final Handler handler, final ArrayNode output,
                            final ObjectMapper objectMapper) {
        Movie currentMovie = handler.getCurrentMovieList().get(0);

        // if the movie was not watched, outputs error
        if (!handler.getCurrentUser().getWatchedMovies().contains(currentMovie)) {
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        // if the movie was already liked, outputs error
        if (handler.getCurrentUser().getLikedMovies().contains(currentMovie)) {
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        // likes the movie
        handler.getCurrentUser().getLikedMovies().add(currentMovie);
        currentMovie.setNumLikes(currentMovie.getNumLikes() + 1);

        output.add(OutputHandler.outputHandler(handler, false, objectMapper));
    }

    /**
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details rates a movie (only on 'see details' page and if the movie was watched)
     * */
    public static void rate(final Handler handler, final ArrayNode output,
                            final ObjectMapper objectMapper) {
        Movie currentMovie = handler.getCurrentMovieList().get(0);

        // if the movie was not watched, outputs error
        if (!handler.getCurrentUser().getWatchedMovies().contains(currentMovie)) {
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        // if the rating is not within the rating range, outputs error
        if (handler.getCurrentAction().getRate() > MAX_RATE
                || handler.getCurrentAction().getRate() < MIN_RATE) {
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        if (currentMovie.getRatingList() == null) {
            currentMovie.setRatingList(new ArrayList<>());
        }

        // if the movie was not rated, adds a new rating, else replaces with the new rating
        if (!handler.getCurrentUser().getRatedMovies().contains(currentMovie)) {
            currentMovie.setNumRatings(currentMovie.getNumRatings() + 1);

            currentMovie.getRatingList().add(handler.getCurrentAction().getRate());
            handler.getCurrentUser().getRatingList().put(currentMovie.getName(),
                    handler.getCurrentAction().getRate());

            handler.getCurrentUser().getRatedMovies().add(currentMovie);
        } else {
            int oldRating = handler.getCurrentUser().getRatingList().get(currentMovie.getName());

            for (int i = 0; i < currentMovie.getRatingList().size(); i++) {
                if (oldRating == currentMovie.getRatingList().get(i)) {
                    currentMovie.getRatingList().set(i, handler.getCurrentAction().getRate());
                    break;
                }
            }

            handler.getCurrentUser().getRatingList().replace(currentMovie.getName(),
                    handler.getCurrentAction().getRate());
        }

        // calculates the new rating of the movie
        double sumRatings = 0;
        for (Integer rating : currentMovie.getRatingList()) {
            sumRatings += rating;
        }

        double movieRating = (sumRatings / currentMovie.getNumRatings());
        currentMovie.setRating(movieRating);

        output.add(OutputHandler.outputHandler(handler, false, objectMapper));
    }

    /**
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details buys a premium account for the current user (gets 15 free movies)
     * */
    public static void buyPremiumAccount(final Handler handler, final ArrayNode output,
                                         final ObjectMapper objectMapper) {
        int userTokens = handler.getCurrentUser().getTokensCount();

        // if the user does not have enough tokens to buy a premium account, outputs error
        if (userTokens - PREMIUM_ACCOUNT_PRICE < 0) {
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        // buys premium account :)
        int newUserTokens = userTokens - PREMIUM_ACCOUNT_PRICE;
        handler.getCurrentUser().setTokensCount(newUserTokens);

        handler.getCurrentUser().getCredentials().setAccountType(ACCOUNT_PREMIUM);
    }

    /**
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details buys given number of tokens using the user's account balance
     * */
    public static void buyTokens(final Handler handler, final ArrayNode output,
                                 final ObjectMapper objectMapper) {
        int userBalance = Integer.parseInt(handler.getCurrentUser().getCredentials().getBalance());
        int tokenNumber = Integer.parseInt(handler.getCurrentAction().getCount());

        // if the user does not have the balance to buy the desired number of tokens, outputs error
        if (userBalance - tokenNumber < 0) {
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        // buys the tokens :)
        int newUserBalance = userBalance - tokenNumber;
        handler.getCurrentUser().getCredentials().setBalance(Integer.toString(newUserBalance));

        int userTokens = handler.getCurrentUser().getTokensCount();
        handler.getCurrentUser().setTokensCount(userTokens + tokenNumber);
    }
}
