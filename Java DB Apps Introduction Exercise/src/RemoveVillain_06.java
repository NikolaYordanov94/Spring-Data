import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain_06 {

    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getSqlConnection();
        Scanner scanner = new Scanner(System.in);

        int villainID = Integer.parseInt(scanner.nextLine());

        PreparedStatement preparedStatementMinionsCount = connection.prepareStatement("select minion_id from minions_villains\n" +
                "where villain_id = ?");
        preparedStatementMinionsCount.setInt(1, villainID);
        ResultSet resultSetMinionsCount = preparedStatementMinionsCount.executeQuery();

        int count = 0;
        while (resultSetMinionsCount.next()){
            count++;
        }

        if (count == 0){
            System.out.println("No such villain was found");
            return;
        }

        PreparedStatement preparedStatementReleaseMinions = connection.prepareStatement("delete from minions_villains\n" +
                "where villain_id = ?");
        preparedStatementReleaseMinions.setInt(1, villainID);
        preparedStatementReleaseMinions.executeUpdate();

        PreparedStatement preparedStatementVillainName = connection.prepareStatement("select name from villains\n" +
                "where id = ?");
        preparedStatementVillainName.setInt(1, villainID);
        ResultSet resultSetVillainName = preparedStatementVillainName.executeQuery();
        resultSetVillainName.next();
        String villainName = resultSetVillainName.getString("name");

        PreparedStatement preparedStatementDeleteVillain = connection.prepareStatement("delete from villains\n" +
                "where id = ?");
        preparedStatementDeleteVillain.setInt(1, villainID);
        preparedStatementDeleteVillain.executeUpdate();

        System.out.printf("%s was deleted\n", villainName);
        System.out.printf("%d minions released", count);

        connection.close();
    }
}
