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

        // Dependencies
        stockDao = new StockDao();
        userDao = new UserDao();

        // Configuration
        port(4567);
        enableDebugScreen();


        // Filters
        before("*",     Filters.addTrailingSlashes);
        before("*",     Filters.handleLocaleChange);
        before("*",     Filters.addCORSHeader);

        // Routes
        get("/stockSummaries/",             StockController.fetchAllStocks);

        post("/stockSummaries/create/", "application/json", (req, res) -> StockController.createAStock(req, res));

        put("/stocks/update/",  "application/json", (req, res) -> StockController.updateAStock(req, res));

        delete("/stocks/delete/", "application/json", (req, res) -> StockController.deleteAStock(req, res));

        get("/users/",          UserController.fetchAllUsers);

        get("*",                     ViewUtil.notFound);

        // Filters
        after("*",                   Filters.addGzipHeader);

    }

}