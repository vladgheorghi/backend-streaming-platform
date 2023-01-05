package page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import database.Database;

import user.Handler;

import static database.Constant.LOGIN;
import static database.Constant.REGISTER;
import static database.Constant.SEARCH;
import static database.Constant.FILTER;
import static database.Constant.PURCHASE;
import static database.Constant.WATCH;
import static database.Constant.LIKE;
import static database.Constant.RATE;
import static database.Constant.BUY_PREMIUM_ACCOUNT;
import static database.Constant.BUY_TOKENS;

/**
 * @class class for accessing a feature in the page
 * @details handles all features. called only when the page contains the instance feature
 * */

public final class PageAccessFeature {
    /**
     * @param mainDatabase -> main database with all data
     * @param handler -> handler for the current user
     * @param output -> adds to this JSON array the object output (error or not)
     * @param objectMapper -> object mapper used to create new JSON objects and arrays
     * @details handles the feature system
     * */
    public static void accessPageFeature(final Database mainDatabase, final Handler handler,
                                         final ArrayNode output, final ObjectMapper objectMapper) {
        String actionFeature = handler.getCurrentAction().getFeature();
        switch (actionFeature) {
            case LOGIN -> PageFeatures.login(mainDatabase, handler, output, objectMapper);
            case REGISTER -> PageFeatures.register(mainDatabase, handler, output, objectMapper);
            case SEARCH -> PageFeatures.search(mainDatabase, handler, output, objectMapper);
            case FILTER -> PageFeatures.filter(mainDatabase, handler, output, objectMapper);
            case PURCHASE -> PageFeatures.purchase(handler, output, objectMapper);
            case WATCH -> PageFeatures.watch(handler, output, objectMapper);
            case LIKE -> PageFeatures.like(handler, output, objectMapper);
            case RATE -> PageFeatures.rate(handler, output, objectMapper);
            case BUY_PREMIUM_ACCOUNT -> PageFeatures.buyPremiumAccount(handler,
                    output, objectMapper);
            case BUY_TOKENS -> PageFeatures.buyTokens(handler, output, objectMapper);
            default -> { }
        }
    }
}
