package database;

/**
 * @class class for constant data
 * @details change constants here if needed
 * */
public final class Constant {
    /** PAGES */
    public static final String HOMEPAGE_UNLOGGED = "homepage unlogged";
    public static final String HOMEPAGE_LOGGED = "homepage logged";
    public static final String LOGIN_PAGE = "login";
    public static final String REGISTER_PAGE = "register";
    public static final String LOGOUT_PAGE = "logout";
    public static final String MOVIES_PAGE = "movies";
    public static final String SEE_DETAILS_PAGE = "see details";
    public static final String UPGRADES_PAGE = "upgrades";

    /** ACTION TYPES */
    public static final String CHANGE_PAGE = "change page";
    public static final String ON_PAGE = "on page";
    public static final String SUBSCRIBE = "subscribe";
    public static final String DATABASE = "database";
    public static final String BACK = "back";

    /** ACTIONS */
    public static final String LOGIN = "login";
    public static final String REGISTER = "register";
    public static final String SEARCH = "search";
    public static final String FILTER = "filter";
    public static final String PURCHASE = "purchase";
    public static final String WATCH = "watch";
    public static final String LIKE = "like";
    public static final String RATE = "rate";
    public static final String BUY_PREMIUM_ACCOUNT = "buy premium account";
    public static final String BUY_TOKENS = "buy tokens";

    /** DATABASE ACTIONS */
    public static final String ADD_MOVIE = "add";
    public static final String DELETE_MOVIE = "delete";

    /** ACCOUNT TYPES */
    public static final String ACCOUNT_STANDARD = "standard";
    public static final String ACCOUNT_PREMIUM = "premium";

    /** SORT TYPES */
    public static final String INCREASING_SORT = "increasing";
    public static final String DECREASING_SORT = "decreasing";

    /** MOVIE RATING RANGE */
    public static final int MAX_RATE = 5;
    public static final int MIN_RATE = 1;

    /** PRICES and other */
    public static final int PREMIUM_ACCOUNT_PRICE = 10;
    public static final int MOVIE_PRICE = 2;
    public static final int FREE_PREMIUM_MOVIES = 15;

    /** NOTIFICATIONS */
    public static final String ADDED_MOVIE = "ADD";
    public static final String DELETED_MOVIE = "DELETED";
}
