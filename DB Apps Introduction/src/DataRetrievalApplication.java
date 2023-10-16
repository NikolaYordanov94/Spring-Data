import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DataRetrievalApplication {

    private static final String SELECT_USER_COUNT_GAMES_BY_USERNAME = "SELECT u.first_name, u.last_name, COUNT(ug.id) AS games "
            + "FROM users AS u "
            + "JOIN users_games AS ug ON u.id = ug.user_id "
            + "WHERE u.user_name = ? "
            + "GROUP BY u.id";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = scanner.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password default (empty):");
        String password = scanner.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt =
                connection.prepareStatement(SELECT_USER_COUNT_GAMES_BY_USERNAME);

        String username = scanner.nextLine();
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        boolean hasRow = rs.next();

        if (hasRow) {
            System.out.printf("%s %s has played %d games%n",
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("games"));
        } else {
            System.out.println("No such user exists");
            }
        connection.close();
    }
}