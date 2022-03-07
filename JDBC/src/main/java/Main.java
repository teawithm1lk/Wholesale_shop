import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = getDBConnection()) {
            if (connection.isValid(1)) {
                System.out.println("Connection complete");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static Connection getDBConnection() throws SQLException {
        String dbURL = "jdbc:sqlite:E:/java projects/coursework-teawithm1lk/JDBC/src/goods.sqlite";
        return DriverManager.getConnection(dbURL);
    }
}
