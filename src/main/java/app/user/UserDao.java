package app.user;

import com.google.common.collect.*;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDao {

    public Iterable<User> getAllUsers() {
        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/?user=root");
        Handle h = dbi.open();

        List<User> users = h.createQuery("SELECT username, email, password, create_time FROM prices.user")
                .map(new ResultSetMapper<User>() {
                    @Override
                    public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
                        return new User(resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("create_time"));
                    }
                })
                .list();

        h.close();
        return users;
    }
}