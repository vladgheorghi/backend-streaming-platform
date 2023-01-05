package user;

import database.Database;
import movie.GetMovie;
import movie.Movie;

import java.util.*;

public class Recommendation {
    /***/
    public static void premiumUserRecommend(final Database mainDatabase, final Handler handler) {
        Notification recommend = new Notification();
        recommend.setMessage("Recommendation");

        Map<String, Integer> topGenres = new HashMap<>();

        for (Movie movie : handler.getCurrentUser().getLikedMovies()) {
            for (String genre : movie.getGenres()) {
                if (!topGenres.containsKey(genre)) {
                    topGenres.put(genre, 1);
                } else {
                    int count = topGenres.get(genre);
                    topGenres.replace(genre, count + 1);
                }
            }
        }

        if (topGenres.isEmpty()) {
            recommend.setMovieName("No recommendation");
            handler.getCurrentUser().getNotifications().add(recommend);
            return;
        }

        List<Map.Entry<String, Integer>> sortedTopGenres = new LinkedList<>(topGenres.entrySet());

        sortedTopGenres.sort((o1, o2) -> {
            if (o1.getValue().compareTo(o2.getValue()) == 0) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return (-1) * o1.getValue().compareTo(o2.getValue());
            }
        });

        GetMovie.getMovies(mainDatabase, handler);

        if (handler.getCurrentMovieList().isEmpty()) {
            recommend.setMovieName("No recommendation");
            handler.getCurrentUser().getNotifications().add(recommend);
            return;
        }

        handler.getCurrentMovieList().sort((o1, o2) -> {
            if (o1.getNumLikes() > o2.getNumLikes()) {
                return -1;
            } else if (o1.getNumLikes() < o2.getNumLikes()) {
                return 1;
            }
            return 0;
        });

        for (Map.Entry<String, Integer> entry : sortedTopGenres) {
            for (Movie movie : handler.getCurrentMovieList()) {
                if (movie.getGenres().contains(entry.getKey())
                        && !handler.getCurrentUser().getWatchedMovies().contains(movie)) {
                    recommend.setMovieName(movie.getName());
                    handler.getCurrentUser().getNotifications().add(recommend);
                    return;
                }
            }
        }

        recommend.setMovieName("No recommendation");
        handler.getCurrentUser().getNotifications().add(recommend);
    }
}
