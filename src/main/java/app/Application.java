package app;

import app.price.PriceController;
import app.price.PriceDao;
import app.stock.StockController;
import app.stock.StockDao;
import app.util.Filters;
import app.util.ViewUtil;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Application {

    // Declare dependencies
    public static StockDao stockDao;
    public static PriceDao priceDao;

    public static void main(String[] args) {

        // Dependencies
        stockDao = new StockDao();
        priceDao = new PriceDao();

        // Configuration
        port(4567);
        enableDebugScreen();


        // Filters
        before("*",     Filters.addTrailingSlashes);
        before("*",     Filters.handleLocaleChange);
        before("*",     Filters.addCORSHeader);

        // Routes
        get( "/stock/summaries/",                                                            StockController.fetchAllStocks);

        post("/stock/create/",                  "application/json", (req, res) -> StockController.createAStock(req, res));

        get( "/prices/:companyID/",             "application/json", (req, res) -> PriceController.fetchAllPrices(req, res));

        get( "/prices/latestPrice/:companyID/", "application/json", (req, res) -> PriceController.fetchLatestPrice(req, res));

        post("/prices/buy/",                    "application/json", (req, res) -> PriceController.buyAStock(req, res));

//        put("/stocks/update/",  "application/json", (req, res) -> StockController.updateAStock(req, res));

//        delete("/stocks/delete/", "application/json", (req, res) -> StockController.deleteAStock(req, res));

        get("*",         ViewUtil.notFound);

        // Filters
        after("*",       Filters.addGzipHeader);

    }

}