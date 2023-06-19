import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AddMinions {
    private static final String PRINT_VILLAIN_ADD_FORMAT = "Villain %s was added to the database.\n";
    private static final String GET_VILLAIN_BY_NAME = "select id from villains where name = ? ";
    private static final String INSERT_INTO_VILLAIN = "insert into villains(name, evilness_factor) value (?, ?) ";
    private static final String EVILNESS_FACTOR = "evil";

    private static final String PRINT_TOWN_ADD_FORMAT = "Town %s was added to the database.\n";
    private static final String GET_TOWN_BY_NAME = "select id from towns where name = ? ";
    private static final String INSERT_INTO_TOWNS = "insert into towns(name) value(?)";

    private static final String GET_LAST_MINION_BY_NAME =
            "select id from minions_db.minions where name = ? order by id desc limit 1";
    private static final String INSERT_MINION = "insert into minions_db.minions(name, age, town_id) values(?,?,?) ";
    private static final String INSERT_MINION_VILLAIN = "insert into minions_villains (minion_id, villain_id) VALUES (?, ?);";

    private static final String PRINT_MINION_TO_VILLAIN_ADD_FORMAT = "Successfully added %s to be minion of %s.\n";

    public static void main(String[] args) throws SQLException {
        final Connection sqlConnection = Utils.getSqlConnection();

        final Scanner scanner = new Scanner(System.in);

        final String[] minionsInfo = scanner.nextLine().split(" ");
        final String minionName = minionsInfo[1];
        final int minionAge = Integer.parseInt(minionsInfo[2]);
        final String minionTownName = minionsInfo[3];

        final String villainName = scanner.nextLine().split(" ")[1];

        int townId = getEntryId(sqlConnection,
                List.of(minionTownName),
                GET_TOWN_BY_NAME,
                INSERT_INTO_TOWNS,
                PRINT_TOWN_ADD_FORMAT);

        int villainId = getEntryId(sqlConnection,
                List.of(villainName, EVILNESS_FACTOR),
                GET_VILLAIN_BY_NAME,
                INSERT_INTO_VILLAIN,
                PRINT_VILLAIN_ADD_FORMAT);

        final PreparedStatement insertMinionStatement = sqlConnection.prepareStatement(INSERT_MINION);
        insertMinionStatement.setString(1, minionName);
        insertMinionStatement.setInt(2, minionAge);
        insertMinionStatement.setInt(3, townId);

        insertMinionStatement.executeUpdate();

        final PreparedStatement lastMinion = sqlConnection.prepareStatement(GET_LAST_MINION_BY_NAME);
        lastMinion.setString(1, minionName);

        final ResultSet resultSet = lastMinion.executeQuery();
        resultSet.next();

        final int minionId = resultSet.getInt("id");

        final PreparedStatement insertStatement = sqlConnection.prepareStatement(INSERT_MINION_VILLAIN);
        insertStatement.setInt(1, minionId);
        insertStatement.setInt(2, villainId);

        insertStatement.executeUpdate();

        System.out.printf(PRINT_MINION_TO_VILLAIN_ADD_FORMAT, minionName, villainName);

        sqlConnection.close();
    }

    private static int getEntryId(Connection sqlConnection, List<String> arguments, String getQuery, String insertQuery, String printVillainAddFormat) throws SQLException {
        final PreparedStatement statement = sqlConnection.prepareStatement(getQuery);
        final String name = arguments.get(0);
        statement.setString(1, name);

        final ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            final PreparedStatement insertStatement = sqlConnection.prepareStatement(insertQuery);

            for (int i = 1; i <= arguments.size(); i++) {
                insertStatement.setString(i, arguments.get(i - 1));
            }

            insertStatement.executeUpdate();

            final ResultSet afterUpdate = statement.executeQuery();
            afterUpdate.next();

            System.out.printf(printVillainAddFormat, name);

            return afterUpdate.getInt("id");
        }

        return resultSet.getInt("id");
    }


}
