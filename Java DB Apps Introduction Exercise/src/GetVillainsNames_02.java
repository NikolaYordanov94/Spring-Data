import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVillainsNames_02 {

    private static final String GET_VILLAINS_NAMES = "SELECT v.name, COUNT(DISTINCT mv.minion_id) AS count_minions " +
            "FROM villains AS v " +
            "JOIN minions_villains AS mv ON v.id = mv.villain_id " +
            "GROUP BY v.id " +
            "HAVING count_minions > ? " +
            "ORDER BY count_minions DESC";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSqlConnection();

        final PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_NAMES);

        statement.setInt(1, 15);
        final ResultSet rs = statement.executeQuery();

        while (rs.next()){
            final String name = rs.getString("name");
            final int count_minions = rs.getInt("count_minions");

            System.out.printf("%s %d", name, count_minions);
        }
        connection.close();

    }
}