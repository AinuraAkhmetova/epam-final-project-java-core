package project.common.solutions.utils;

import project.common.solutions.connectionPool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static project.common.solutions.connectionPool.ConnectionPool.*;


public final class DbUtils {

    private DbUtils() {

    }


    public static boolean transaction(String sql,
                                      JdbcConsumer<PreparedStatement> psConsumer,
                                      String sql2,
                                      JdbcConsumer<PreparedStatement> psConsumer2) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool
                    .getInstance().getConnection();
            connection.setAutoCommit(false);
            //int affectedRowsCargo = executeUpdate(sql, psConsumer);


            PreparedStatement ps = connection.prepareStatement(sql);
            psConsumer.accept(ps);
            int affectedRows=ps.executeUpdate();

            ps = connection.prepareStatement(sql2);
            psConsumer2.accept(ps);
            int affectedRows2=ps.executeUpdate();


            if ((affectedRows == 1) && (affectedRows2 == 1)) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
            }

        } catch (Exception e) {
            if (connection != null) {
                System.out.println("Revert ");
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
                return false;
            }
        }
        return false;
    }


    public static int executeUpdate(String sql,
                                    JdbcConsumer<PreparedStatement> psConsumer) {
        try (Connection con = getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            psConsumer.accept(ps);
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> selectAll(String sql,
                                        JdbcFunction<ResultSet, T> rsConverter) {
        try (Connection con = getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ResultSet resultSet = ps.executeQuery();

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = rsConverter.apply(resultSet);
                result.add(t);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> selectByName(String sql, String name,
                                           JdbcFunction<ResultSet, T> rsConverter) {
        int i = 0;
        try (Connection con = getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);

        ) {
            ps.setString(++i, name);
            ResultSet resultSet = ps.executeQuery();

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = rsConverter.apply(resultSet);
                result.add(t);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> selectById(String sql, Long id,
                                         JdbcFunction<ResultSet, T> rsConverter) {
        int i = 0;
        try (Connection con = getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);

        ) {
            ps.setLong(++i, id);
            ResultSet resultSet = ps.executeQuery();

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = rsConverter.apply(resultSet);
                result.add(t);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
