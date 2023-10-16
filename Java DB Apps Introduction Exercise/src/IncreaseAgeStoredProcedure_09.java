import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure_09 {
    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSqlConnection();
        Scanner scanner = new Scanner(System.in);

        int minionID = Integer.parseInt(scanner.nextLine());

        PreparedStatement preparedStatementGetOlderProcedure = connection.prepareStatement("call usp_get_older(?)");
        preparedStatementGetOlderProcedure.setInt(1, minionID);
        preparedStatementGetOlderProcedure.executeUpdate();

        PreparedStatement preparedStatementPrintResult = connection.prepareStatement("select name, age from minions\n" +
                "where id = ?");
        preparedStatementPrintResult.setInt(1, minionID);
        ResultSet resultSetPrint = preparedStatementPrintResult.executeQuery();

        resultSetPrint.next();
        System.out.printf("%s %d", resultSetPrint.getString("name"), resultSetPrint.getInt("age"));

        connection.close();
    }
}
