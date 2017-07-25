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
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/?user=root");
        Handle h = dbi.open();

        List<Stock> stocks = h.createQuery("SELECT id, name, price FROM prices.companies")
                .map(new ResultSetMapper<Stock>() {
                    @Override
                    public Stock map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                        return new Stock(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price"));
                    }
                })
                .list();

        h.close();
        return stocks;
    }

    public Iterable<Stock> createStock() {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/?user=root");
        Handle h = dbi.open();

        h.execute("INSERT INTO `prices`.`companies` (`name`, `price`) VALUES ('Goji', '50')");

        List<Stock> stocks = h.createQuery("SELECT id, name, price FROM prices.companies")
                .map(new ResultSetMapper<Stock>() {
                    @Override
                    public Stock map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                        return new Stock(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price"));
                    }
                })
                .list();

        h.close();
        return stocks;
    }

    public Iterable<Stock> updateStock() {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/?user=root");
        Handle h = dbi.open();

        h.execute("UPDATE `prices`.`companies` SET `price`='150' WHERE `id`='2'");

        List<Stock> stocks = h.createQuery("SELECT id, name, price FROM prices.companies")
                .map(new ResultSetMapper<Stock>() {
                    @Override
                    public Stock map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                        return new Stock(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price"));
                    }
                })
                .list();

        h.close();
        return stocks;
    }
}