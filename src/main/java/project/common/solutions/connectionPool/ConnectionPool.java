package project.common.solutions.connectionPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool implements Connectible {

    private static final ConnectionPool INSTANCE = new ConnectionPool();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;


    private static String URL = "jdbc:h2:mem:sample;INIT=RUNSCRIPT FROM 'classpath:homework_23/db_config/create-schema.sql'\\;" +
            "RUNSCRIPT FROM 'classpath:homework_23/db_config/init-data.sql'";

    static {
        config.setJdbcUrl(URL);
        config.setUsername("ainura");
        config.setPassword("1806");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
