package page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import output.OutputHandler;
import user.Handler;

import static database.Constant.SEE_DETAILS_PAGE;

public class GenreSubscribe {
    public static void subscribe(Handler handler, ArrayNode output, ObjectMapper objectMapper) {
        if (!handler.getCurrentPage().getName().equals(SEE_DETAILS_PAGE)) {
            // outputs error in JSON file if page is not "see details"
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        String genre = handler.getCurrentAction().getSubscribedGenre();

        if (!handler.getCurrentMovieList().get(0).getGenres().contains(genre)) {
            // outputs error in JSON file if the movie does not contain the selected genre
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        if (handler.getCurrentUser().getSubscribedGenres().contains(genre)) {
            // outputs error in JSON file if the user already subscribed to that genre
            output.add(OutputHandler.outputHandler(handler, true, objectMapper));
            return;
        }

        handler.getCurrentUser().getSubscribedGenres().add(genre);
    }
}
