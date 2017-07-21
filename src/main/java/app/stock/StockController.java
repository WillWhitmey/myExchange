package app.stock;

import app.util.*;
import spark.*;
import static app.Application.stockDao;
import static app.util.JsonUtil.*;

public class StockController {

    public static Route fetchAllStocks = (Request request, Response response) -> {
        return dataToJson(stockDao.getAllStocks());
    };
}
