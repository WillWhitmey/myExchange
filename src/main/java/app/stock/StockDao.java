package app.stock;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class StockDao {

    public StockSummary getAllStocks() {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/stockify?user=root");
        Handle h = dbi.open();

        List<Stock> stocks = h.createQuery("SELECT * FROM Companies")
                .map(new ResultSetMapper<Stock>() {
                    @Override
                    public Stock map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                        return new Stock(resultSet.getString("CompanyID"), resultSet.getString("Name"));
                    }
                })
                .list();

        h.close();
        return new StockSummary(stocks);
    }

    public StockSummary createStock(Stock stock) {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/stockify?user=root&relaxAutoCommit=true");
        Handle h = dbi.open();

        try (Handle handle = dbi.open()) {

            String uniqueID = UUID.randomUUID().toString();

            stock.setId(uniqueID);

            h.execute("INSERT INTO `prices`.`companies` (`id`, `name`, `price`, `image`) VALUES (?, ?, ?, ?)",
                    stock.getId(),
                    stock.getName());

            List list = java.util.Arrays.asList(stock);

            return new StockSummary(list);
        }
    }

}