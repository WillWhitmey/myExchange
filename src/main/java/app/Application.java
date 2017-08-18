package app;

import app.stock.*;
import app.user.*;
import app.util.*;
import spark.Filter;
import spark.Spark;

import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

public class Application {

    // Declare dependencies
    public static StockDao stockDao;
    public static UserDao userDao;

    public static void main(String[] args) {

        // Instantiate your dependencies
        stockDao = new StockDao();
        userDao = new UserDao();

        // Configure Spark
        port(4567);
        enableDebugScreen();


        // Set up before-filters (called before each get/post)
        before("*",                  Filters.addTrailingSlashes);
        before("*",                  Filters.handleLocaleChange);
        before("*", Filters.addCORSHeader);

        // Set up routes
        get("/stocks/",         StockController.fetchAllStocks);
        post("/stocks/create/", "application/json", (req, res) -> { return StockController.createAStock(req, res);});
        put("/stocks/update/",  "application/json", (req, res) -> { return StockController.updateAStock(req, res);});
        delete("/stocks/delete/", "application/json", (req, res) -> { return StockController.deleteAStock(req, res);});
        get("/users/",          UserController.fetchAllUsers);
        get("*",                     ViewUtil.notFound);

        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);

    }

}