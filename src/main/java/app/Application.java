package app;

import app.stock.*;
import app.util.*;
import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

public class Application {

    // Declare dependencies
    public static StockDao stockDao;

    public static void main(String[] args) {

        // Instantiate your dependencies
        stockDao = new StockDao();

        // Configure Spark
        port(4567);
        enableDebugScreen();

        // Set up before-filters (called before each get/post)
        before("*",                  Filters.addTrailingSlashes);
        before("*",                  Filters.handleLocaleChange);

        // Set up routes
        get("/stocks/",         StockController.fetchAllStocks);
        get("*",                     ViewUtil.notFound);

        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);

    }

}
