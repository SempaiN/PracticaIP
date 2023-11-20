package Data;

import java.sql.*;

public class ConnectionSQL {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/poidb?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWD);
    }

    public static void close(ResultSet resul) throws SQLException {
        resul.close();
    }

    public static void close(Statement state) throws SQLException {
        state.close();
    }

    public static void close(Connection conn) throws SQLException {
        conn.close();
    }

}
