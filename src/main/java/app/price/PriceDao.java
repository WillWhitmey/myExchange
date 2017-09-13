package app.price;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PriceDao {

    public PriceSummary getAllPrices(String companyID) {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/stockify?user=root");
        Handle h = dbi.open();

        List<Price> prices = h.createQuery("SELECT * FROM Prices WHERE CompanyID=:id ORDER BY Time ASC").bind("id", companyID)
                .map(new ResultSetMapper<Price>() {
                    @Override
                    public Price map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                        return new Price(resultSet.getString("PriceID"), resultSet.getInt("Price"), resultSet.getLong("Time"), resultSet.getString("CompanyID"));
                    }
                })
                .list();

        h.close();
        return new PriceSummary(prices);
    }

    public PriceSummary getLatestPrice(String companyID) {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/stockify?user=root");
        Handle h = dbi.open();

        List<Price> prices = h.createQuery("SELECT * FROM Prices WHERE CompanyID=:id ORDER BY Time DESC LIMIT 1").bind("id", companyID)
                .map(new ResultSetMapper<Price>() {
                    @Override
                    public Price map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                        return new Price(resultSet.getString("PriceID"), resultSet.getInt("Price"), resultSet.getLong("Time"), resultSet.getString("CompanyID"));
                    }
                })
                .list();

        h.close();
        return new PriceSummary(prices);
    }

    public PriceSummary buyStock(Price price) {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/stockify?user=root&relaxAutoCommit=true");
        Handle h = dbi.open();

        try (Handle handle = dbi.open()) {

            String uniqueID = UUID.randomUUID().toString();

            price.setId(uniqueID);

            h.execute("INSERT INTO Prices (`PriceID`, `Price`, `Time`, `CompanyID`) VALUES (?, ?, ?, ?)",
                    price.getId(),
                    price.getPrice(),
                    price.getTime(),
                    price.getCompanyID());

            List list = java.util.Arrays.asList(price);

            return new PriceSummary(list);
        }
    }

}
