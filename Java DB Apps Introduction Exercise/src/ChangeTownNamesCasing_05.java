import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing_05 {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String country = scanner.nextLine();
        final Connection connection = Utils.getSqlConnection();
        List<String> printResult = new ArrayList<>();

        final PreparedStatement preparedStatementCheckCountry = connection.prepareStatement("select count(*) as count from towns where country = ?");
        preparedStatementCheckCountry.setString(1, country);
        final ResultSet resultSetCount = preparedStatementCheckCountry.executeQuery();
        resultSetCount.next();
        int rowCount = resultSetCount.getInt("count");

        if (rowCount == 0) {
            System.out.println("No town names were affected.");
        } else {
            final PreparedStatement preparedStatementGetTownNames = connection.prepareStatement("select name from towns where country = ?");
            preparedStatementGetTownNames.setString(1, country);
            final ResultSet resultSetTownNames = preparedStatementGetTownNames.executeQuery();

            int counter = 0;
            while (resultSetTownNames.next()){
                counter++;
                printResult.add(resultSetTownNames.getString("name"));
            }

            String result = "[" + String.join(", ", printResult) + "]";
            System.out.printf("%d town names were affected.%n", counter);
            System.out.println(result);
        }
        connection.close();
    }
}