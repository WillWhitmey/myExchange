package app.stock;

import com.google.common.collect.*;
import spark.*;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StockDao {

    public StockSummary getAllStocks() {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/?user=root");
        Handle h = dbi.open();

        List<Stock> stocks = h.createQuery("SELECT id, name, price, image FROM prices.companies")
                .map(new ResultSetMapper<Stock>() {
                    @Override
                    public Stock map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                        return new Stock(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("price"), resultSet.getString("image"));
                    }
                })
                .list();

        h.close();
        return new StockSummary(stocks);
    }

    public StockSummary createStock(Stock stock) {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/?user=root&relaxAutoCommit=true");
        Handle h = dbi.open();

        try (Handle handle = dbi.open()) {

            String uniqueID = UUID.randomUUID().toString();

            stock.setId(uniqueID);

            h.execute("INSERT INTO `prices`.`companies` (`id`, `name`, `price`, `image`) VALUES (?, ?, ?, ?)",
                    stock.getId(),
                    stock.getName(),
                    stock.getPrice(),
                    stock.getImage());

            List list = java.util.Arrays.asList(stock);

            return new StockSummary(list);
        }
    }

    public PurchaseSummary buyStock(Purchase purchase) {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/?user=root&relaxAutoCommit=true");
        Handle h = dbi.open();

        try (Handle handle = dbi.open()) {

            String uniqueID = UUID.randomUUID().toString();

            purchase.setId(uniqueID);

            h.execute("INSERT INTO `prices`.`prices` (`id`, `price`, `time`, `companyID`) VALUES (?, ?, ?, ?)",
                    purchase.getId(),
                    purchase.getPrice(),
                    purchase.getTime(),
                    purchase.getCompanyID());

            List list = java.util.Arrays.asList(purchase);

            return new PurchaseSummary(list);
        }
    }

    public Iterable<Stock> updateStock(Stock stock) {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/?user=root&relaxAutoCommit=true");
        Handle h = dbi.open();

        try (Handle handle = dbi.open()) {
//            h.begin();

            h.execute("UPDATE `prices`.`companies` SET `price`=? WHERE `id`=?",
                    stock.getPrice(),
                    stock.getId());

//            handle.commit();

            return h.createQuery("SELECT id, name, price, image FROM prices.companies")
                    .map(new ResultSetMapper<Stock>() {
                        @Override
                        public Stock map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                            return new Stock(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("price"), resultSet.getString("image"));
                        }
                    })
                    .list();
        }
//        } catch (Exception e) {
//            h.rollback();
//            throw new RuntimeException(e);
//        }

    }

    public Iterable<Stock> deleteStock(Stock stock) {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/?user=root&relaxAutoCommit=true");
        Handle h = dbi.open();

        try (Handle handle = dbi.open()) {

            h.execute("DELETE FROM `prices`.`companies` WHERE id=?",
                    stock.getId());

            return h.createQuery("SELECT id, name, price, image FROM prices.companies")
                    .map(new ResultSetMapper<Stock>() {
                        @Override
                        public Stock map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                            return new Stock(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("price"), resultSet.getString("image"));
                        }
                    })
                    .list();
        }
//        } catch (Exception e) {
//            h.rollback();
//            throw new RuntimeException(e);
//        }

    }
}