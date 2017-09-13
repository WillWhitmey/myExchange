package app.price;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import spark.Request;
import spark.Response;

import java.io.IOException;

import static app.Application.priceDao;
import static app.util.JsonUtil.dataToJson;

public class PriceController {

    public static Object fetchAllPrices(Request req, Response res) throws IOException {
        return dataToJson(priceDao.getAllPrices(req.params("companyID")));
    }

    public static Object fetchLatestPrice(Request req, Response res) throws IOException {
        return dataToJson(priceDao.getLatestPrice(req.params("companyID")));
    }

    public static Object buyAStock(Request req, Response res) throws IOException {
        String json = req.body();
        ObjectMapper mapper = new ObjectMapper();
        ObjectReader reader = mapper.reader(Price.class).withRootName("price");
        Price price = reader.readValue(json);

        return dataToJson(priceDao.buyStock(price));
    }
}
