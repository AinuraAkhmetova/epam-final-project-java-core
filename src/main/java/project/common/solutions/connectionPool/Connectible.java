package project.common.solutions.connectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public interface Connectible {
    Connection getConnection() throws SQLException;
}
