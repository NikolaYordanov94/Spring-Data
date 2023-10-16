import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames_03 {
private  static final String GET_MINIONS_NAMES = "select m.name, m.age from villains as v\n" +
        "join minions_villains as mv on v.id = mv.villain_id\n" +
        "join minions as m on mv.minion_id = m.id\n" +
        "where villain_id = ?;";

private static final String GET_VILLAIN_NAME = "select v.name from villains as v\n" +
        "where v.id = ?;";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        final Connection connection = Utils.getSqlConnection();

        final PreparedStatement statementMinions = connection.prepareStatement(GET_MINIONS_NAMES);
        final PreparedStatement statementVillain = connection.prepareStatement(GET_VILLAIN_NAME);

        int villainId = scanner.nextInt();

        statementMinions.setInt(1, villainId);
        statementVillain.setInt(1, villainId);

        final ResultSet rsMinions = statementMinions.executeQuery();
        final ResultSet rsVillain = statementVillain.executeQuery();

        if(!rsVillain.next()){
            System.out.println("No villain with ID 10 exists in the database.");
            return;
        }

        System.out.printf("Villain: %s%n", rsVillain.getString("name"));
        int counter = 0;

        while (rsMinions.next()){
            final String minionName = rsMinions.getString("name");
            final int minionAge = rsMinions.getInt("age");
            counter++;

            System.out.printf("%d. %s %d%n", counter, minionName, minionAge);
        }
        connection.close();
    }
}
