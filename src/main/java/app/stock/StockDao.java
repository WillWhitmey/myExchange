package app.stock;

import com.google.common.collect.*;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StockDao {

//    private final List<Stock> stocks = ImmutableList.of(
//            new Stock("Apple", "Tech", 100),
//            new Stock("Google", "Tech", 400),
//            new Stock("Goji", "FinTech", 600),
//            new Stock("Nutmeg", "Finance", 20)
//    );

    public Iterable<Stock> getAllStocks() {
        DBI dbi = new DBI("jdbc:mysql://localhost:3306", "app", "password");
        Handle h = dbi.open();

        List<Stock> stocks = h.createQuery("SELECT name, price FROM prices.new_table")
                .map(new ResultSetMapper<Stock>() {
                    @Override
                    public Stock map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                        return new Stock(resultSet.getString("name"), resultSet.getInt("price"));
                    }
                })
                .list();

        h.close();
        return stocks;
    }
}
