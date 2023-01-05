package output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import movie.Movie;

import user.Handler;
import user.Notification;

/**
 * @class class for handling output
 * @details parses desired output data inside the JSON ArrayNode
 * */

public final class OutputHandler {
    /**
     * @param handler -> handler for the current user
     * @param error -> true if output should be an error
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @return ObjectNode -> returns an object with the output for the action
     * */
    public static ObjectNode outputHandler(final Handler handler, final boolean error,
                                           final ObjectMapper objectMapper) {
        ObjectNode outputNode = objectMapper.createObjectNode();
        ArrayNode currentMoviesList = objectMapper.createArrayNode();

        // if an error occurred
        if (error) {
            outputNode.put("error", "Error");
            outputNode.set("currentMoviesList", currentMoviesList);
            outputNode.set("currentUser", null);

            return outputNode;
        }

        outputNode.set("error", null);

        for (Movie movie : handler.getCurrentMovieList()) {
            currentMoviesList.add(Parser.parseMovie(movie, objectMapper));
        }

        for (Notification notification : handler.getCurrentUser().getNotifications()) {
            if (notification.getMessage().equals("Recommendation")) {
                currentMoviesList = null;
                break;
            }
        }
        outputNode.set("currentMoviesList", currentMoviesList);

        if (handler.getCurrentUser() == null) {
            outputNode.set("currentUser", null);
        } else {
            ObjectNode currentUser = Parser.parseUser(handler.getCurrentUser(), objectMapper);
            outputNode.set("currentUser", currentUser);
        }

        return outputNode;
    }

}
