package app.stock;

import app.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import static app.Application.stockDao;
import static app.util.JsonUtil.*;

public class StockController {

    public static Route fetchAllStocks = (Request request, Response response) -> {
        ObjectMapper objectMapper = new ObjectMapper();
        return dataToJson(stockDao.getAllStocks());
    };
    public static Object createAStock(Request req, Response res) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(req.body());

        Stock stock = objectMapper.readValue(req.body(), Stock.class);

        return dataToJson(stockDao.createStock(stock));
    }
    public static Object updateAStock(Request req, Response res) throws IOException {
        Set name = req.attributes();
        System.out.println(req.body());

        ObjectMapper objectMapper = new ObjectMapper();

        Stock stock = objectMapper.readValue(req.body(), Stock.class);

        stock.getPriceAtDate();
        return dataToJson(stockDao.updateStock(stock));
    }
    public static Object deleteAStock(Request req, Response res) throws IOException {
        Set name = req.attributes();
        System.out.println(req.body());

        ObjectMapper objectMapper = new ObjectMapper();

        Stock stock = objectMapper.readValue(req.body(), Stock.class);

        stock.getPriceAtDate();
        return dataToJson(stockDao.deleteStock(stock));
    }
}
