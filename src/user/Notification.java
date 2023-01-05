package user;

/**
 * @class class for user notifications
 * @details store notification messages
 * */

public class Notification {
    private String movieName; /** name of the movie */
    private String message; /** notification message (e.g. "ADD", "DELETE") */

    /** GETTERS and SETTERS */
    public final String getMovieName() {
        return movieName;
    }

    public final void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(final String message) {
        this.message = message;
    }

    public Notification(String movieName, String message) {
        this.movieName = movieName;
        this.message = message;
    }
}
