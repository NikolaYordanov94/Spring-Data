import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintAllMinionNames_07 {
    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSqlConnection();

        PreparedStatement preparedStatementMinionNames = connection.prepareStatement("select name from minions",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSetMinionNames = preparedStatementMinionNames.executeQuery();

        int countMinions = 0;
        while (resultSetMinionNames.next()){
            countMinions++;
        }

        resultSetMinionNames.beforeFirst();

        int firstIndex = 1;
        int lastIndex = countMinions;

        for (int i = 1; i < countMinions + 1; i++) {
            if(i % 2 != 0){
                resultSetMinionNames.absolute(firstIndex);
                firstIndex++;
            }else{
                resultSetMinionNames.absolute(lastIndex);
                lastIndex--;
            }
            System.out.println(resultSetMinionNames.getString("name"));
        }
        connection.close();
    }
}
