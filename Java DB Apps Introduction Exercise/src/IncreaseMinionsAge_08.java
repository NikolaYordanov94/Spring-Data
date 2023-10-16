import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge_08 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = Utils.getSqlConnection();

        int[] minionNames = Arrays.stream(scanner.nextLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < minionNames.length; i++) {
            int currentMinionID = minionNames[i];

            PreparedStatement preparedStatementUpdateAge = connection.prepareStatement("update minions\n" +
                    "set age = age + 1\n" +
                    "where id = ?");
            preparedStatementUpdateAge.setInt(1, currentMinionID);
            preparedStatementUpdateAge.executeUpdate();

            PreparedStatement preparedStatementUpdateName = connection.prepareStatement("update minions\n" +
                    "set name = lower(name)\n" +
                    "where id = ?");
            preparedStatementUpdateName.setInt(1, currentMinionID);
            preparedStatementUpdateName.executeUpdate();
        }

        PreparedStatement preparedStatementGetAllMinions = connection.prepareStatement("select name, age from minions");
        ResultSet resultSetGetAllMinions = preparedStatementGetAllMinions.executeQuery();

        while (resultSetGetAllMinions.next()){
            System.out.printf("%s %d\n",
                    resultSetGetAllMinions.getString("name"), resultSetGetAllMinions.getInt("age"));
        }

        connection.close();
    }
}
