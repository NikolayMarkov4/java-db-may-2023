import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVillainsNames {
    private static final String GET_VILLAINS_NAMES = "select v.name, count(distinct mv.minion_id) count_of_minions" +
            " from villains as v" +
            " join minions_villains mv on v.id = mv.villain_id" +
            " group by v.id" +
            " having count_of_minions > ?" +
            " order by count_of_minions DESC;";

    private static final String PRINT_FORMAT = "%s %d";
    private static final String COLUMN_LABEL_COUNT_OF_MINIONS = "count_of_minions";
    private static final String COLUMN_LABEL_NAME = "name";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSqlConnection();

        final PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_NAMES);
        statement.setInt(1, 15);

        final ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            print(resultSet);
        }

        connection.close();
    }

    private static void print(ResultSet resultSet) throws SQLException {
        final String name = resultSet.getString(COLUMN_LABEL_NAME);
        final int count_of_minions = resultSet.getInt(COLUMN_LABEL_COUNT_OF_MINIONS);

        System.out.printf(PRINT_FORMAT, name, count_of_minions);
    }
}
