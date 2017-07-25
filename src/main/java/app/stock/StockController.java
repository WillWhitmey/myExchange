package app.stock;

import app.util.*;
import spark.*;
import static app.Application.stockDao;
import static app.util.JsonUtil.*;

public class StockController {

    public static Route fetchAllStocks = (Request request, Response response) -> {
        return dataToJson(stockDao.getAllStocks());
    };
    public static Route createAStock = (Request request, Response response) -> {
        return dataToJson(stockDao.createStock());
    };
    public static Route updateAStock = (Request request, Response response) -> {
        return dataToJson(stockDao.updateStock());
    };
}
