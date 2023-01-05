/**
 * @author Doru-Vlad Gheorghiu, Faculty of Automatic Control and Computer Science (Year 2)
 * @contact 21gheorghi@gmail.com
 * @date 16th December 2022
 * @purpose Phase 1 Project for the OOP course
 * */
import action.Action;

import action.ActionHandler;
import output.OutputHandler;

import page.PageInitialization;
import page.PageChange;
import page.Page;
import page.PageAccessFeature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import database.Database;

import user.Handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static database.Constant.HOMEPAGE_UNLOGGED;
import static database.Constant.CHANGE_PAGE;
import static database.Constant.ON_PAGE;

public class Main {
    /**@param args -> [0] absolute path of JSON input file
     *              -> [1] JSON output file
     * @function main function for the project
     * @details implemented a Netflix-like program, using JSON files for the I/O. It contains a
     * user system, page system, different actions etc. */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // output JSON objects will be here
        ArrayNode output = objectMapper.createArrayNode();

        /* database where the JSON parsing is done (contains all registered users, details about
        movies, the desired actions and the page system implemented as a map for easier access) */
        Database mainDatabase = objectMapper.readValue(new File(args[0]), Database.class);
        mainDatabase.setPageMap(PageInitialization.initializePageSystem().getPageMap());

        /* handler that actively stores the current logged user, the page he's currently on, what
        movies he can see at a certain point, the current action etc. */
        Handler handler = new Handler(null, mainDatabase.getPageMap().get(HOMEPAGE_UNLOGGED),
                null, new ArrayList<>());

        // going through each action
        ArrayList<Action> actions = mainDatabase.getActions();
        for (Action action : actions) {
            // handler stores current action
            handler.setCurrentAction(action);

            ActionHandler.handleAction(mainDatabase, handler, output, objectMapper);
        }

        // outputs the current object ArrayNode into the output JSON file
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
