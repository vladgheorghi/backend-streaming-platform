package database;

import user.Notification;
import user.User;

import java.util.ArrayList;

/**
 * @class class for giving users a notification
 * */

public final class DatabaseNotificationsService {
    private final ArrayList<User> notifiedUsers;

    public DatabaseNotificationsService() {
        notifiedUsers = new ArrayList<>();
    }

    /**
     * @param user -> user to be notified
     * @param notification -> the notification that the user will get
     * */

    public void notifyUser(final User user, final Notification notification) {
        user.getNotifications().add(notification);
    }

    public ArrayList<User> getNotifiedUsers() {
        return notifiedUsers;
    }
}
