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

    public Iterable<Stock> createStock(Stock stock) {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/?user=root&relaxAutoCommit=true");
        Handle h = dbi.open();

        try (Handle handle = dbi.open()) {

            h.execute("INSERT INTO `prices`.`companies` (`name`, `price`) VALUES (?, ?)",
                    stock.getName(),
                    stock.getPrice());

            return h.createQuery("SELECT id, name, price FROM prices.companies")
                    .map(new ResultSetMapper<Stock>() {
                        @Override
                        public Stock map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                            return new Stock(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price"));
                        }
                    })
                    .list();
        }
//        } catch (Exception e) {
//            h.rollback();
//            throw new RuntimeException(e);
//        }

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

            return h.createQuery("SELECT id, name, price FROM prices.companies")
                    .map(new ResultSetMapper<Stock>() {
                        @Override
                        public Stock map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                            return new Stock(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price"));
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