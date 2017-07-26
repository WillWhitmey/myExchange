package app.stock;

import app.util.*;
import spark.*;
import static app.Application.stockDao;
import static app.util.JsonUtil.*;

public class StockController {

    public static Route fetchAllStocks = (Request request, Response response) -> {
        return dataToJson(stockDao.getAllStocks());
    };
    public static Object createAStock(Request req, Response res) {
        String name = req.body();
        System.out.println(name);
        return dataToJson(stockDao.createStock(name));
    };
    public static Route updateAStock = (Request request, Response response) -> {
        return dataToJson(stockDao.updateStock());
    };
}
