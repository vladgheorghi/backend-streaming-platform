##### 16th December 2022
# <ins> Back-end Streaming Platform </ins>
### Doru-Vlad Gheorghiu
#### Faculty of Automatic Control and Computer Science (Year 2)

[Project Documentation](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2)
 | [Github repository](https://github.com/vladgheorghi/poo-project-phase2)

### <ins> Description </ins>
Implemented a simple backend of a streaming platform like Netflix.
The program parses user, movie and action data from **JSON files** into the `Database` class.
The user can log in or register into the platform, go through a page hierarchy
and do specific actions on a certain page. The program, then, parses a specific
output for the actions or errors into **JSON objects and arrays** and writes them
into an output file.

### <ins> Other details </ins>
* Used 4 Design Patterns:
  * **_Singleton_** (classes: `Database`)
  * **_Factory_** (classes: `Page`, all classes inside `pagelist` package)
  * **_Observer_** (classes: `AddMovieListener`, `DeleteMovieListener`, `DatabaseListener`,
  `DatabaseNotificationsService`, `DatabaseActions`)
  * **_Visitor_** (classes: `CreatePage`, `VisitorCreatePage`, `InitNextPages`, `VisitorInitNextPages`,
  `Page`, all clases inside `pagelist` package)
* `args[0]` contains the **absolute path** of the **JSON input file**
* `args[1]` contains the **JSON output file**
* `handler` instance from `Handler` class is used for controlling the flow of a
specific user on the platform
  * `currentUser` - current user logged on the platform (null if not logged)
  * `currentPage` - current page that the user is on
  * `currentAction` - current action that is applied on a certain page
  * `currentMovieList` - array list with the movies that can be seen by the user
at a certain point in the program (e.g. inside _Movies_ or _See details_ page)
* `mainDatabase` instance of `Database` stores all data parsed from the **JSON input file**
* `pageMap` contains the hierarchy of pages on the platform. Implemented as a map
for easier access
* `PageDatabase` class creates only one instance of the page hierarchy (we don't
want more), so it follows the **_Singleton Design Pattern_**

### <ins> Classes </ins>
* `Main` starting point of the program. Calling the parsing and page initialization
from here. Going through each action and at the end parses **JSON objects**
into the output file
* `User` class for user data
* `Handler` class for the handler explained earlier
* `Credentials` class for storing user credentials (used for login and
register actions)
* `Page` class for page data (e.g. features on the page, next pages that
can be accessed)
* `PageAccessFeature` contains a method that calls all features from here
  (called from `Main`)
* `PageChange` contains the change page method
* `PageDatabase` has the `pageMap` instance (only one). Created following
the **_Singleton Design Pattern_**
* `PageFeatures` here are all the features methods that are called from
`PageAccessFeature` (e.g. login, register, purchase, filter)
* `PageInitialization` here `pageMap` is initialized (returns the `PageDatabase`
instance)
* `pagelist.pkg` here are the classes for creating each page
* `OutputHandler` JSON object parsing is here
* `Parser` contains methods for parsing specific data types
* `GetMovie` contains methods for returning movie lists at certain points in the program
* `Movie` class for movie data
* `ConfirmFilter` boolean methods for verifying if a movie contains certain
actors or genres (called from `filter()`)
* `Filter` contains filter data
* `MovieContain` has information about how to filter the current movie list
* `MovieSort` has information about how to sort the current movie list
* `Sort` contains methods for sorting the current movie list
* `Constant` contains all constant used in the program
* `Database` contains all data parsed from the **JSON input file**
* `Action` class for action data
* `ActionHandler` class for handling each action type
* `AddMovieListener` class for notifying users of the addition of
a new movie into the database (part of the **_Observer Design Pattern_**)
* `DatabaseActions` class for handling database actions 
* `DatabaseListener` interface for notifying users of a certain modification into the database
* `DatabaseNotificationsService` class for notifying users of a modification into the database
* `DeleteMovieListener` class for notifying users of the removal of a movie from the database
* `CreatePage` class that holds all methods for creating each page
* `VisitorCreatePage` interface that holds all methods declarations (implemented by the `CreatePage` class)
(follows the **_Visitor Design Pattern_**)
* `InitNextPages` class that holds all methods for initializing each page's next available pages list
* `VisitorInitNextPages` interface that holds all methods declarations (implemented by the `InitNextPages` class)
(follows the **_Visitor Design Pattern_**)
* `Notification` class for creating notifications
* `Recommendation` class for sending personalised recommendations to premium users at the end of all actions

### <ins> How does it work? </ins>
* Starting from `Main`, the program goes through each action and handles it in `ActionHandler` class
* In `ActionHandler` class:
  * if action type is **change page**, goes to `PageChange` class 
    * changes to the selected page if all conditions are met (page exists, current page contains next page etc.)
    * if a movie list needs to be returned, the program can go to the `GetMovie` class
  * if action type is **on page**, goes to `PageAccessFeature` class
    * handles all on page feature cases and for each feature the program accesses a method inside `PageFeatures` class
  * if action type is **database**, goes to `DatabaseActions` class
    * a movie can be added into the database, so `addMovie()` is called
    * a movie can be removed from the database so `deleteMovie()` is called
  * if action type is **back**, goes to `PageChange` class with variable `back` mode set to `true`
    * same as **change page**, it jusst changes the page using a list with the previous accessed pages inside `handler`
* After all actions are completed, if the last user has a premium account, they get a personalised recommendation based on liked
genres (inside the `Recommendation` class)
* Throughout the program, if an output is needed, class `OutputHandler` is accessed
  * here the `Parser` class can be accessed, which is responsible to parse an complex object into a JSON node
  * after the parsing is done, the node is added to `output` ArrayNode which is shown in the output JSON file
