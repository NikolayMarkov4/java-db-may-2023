import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {

    private static final String GET_OLDER_PROCEDURE = "{CALL usp_get_older (?)}";
    private static final String GET_MINION_NAME_AND_AGE_BY_ID = "SELECT m.name, m.age FROM minions_db.`minions` AS m WHERE id = ?";
    private static final String PRINT_MINION_FORMAT = "%s %d%n";

    public static void main(String[] args) throws SQLException {

        final int minionId = new Scanner(System.in).nextInt();

        final Connection connection = Utils.getSqlConnection();

        final CallableStatement getOlderStoredProcedure = connection.prepareCall(GET_OLDER_PROCEDURE);
        getOlderStoredProcedure.setInt(1, minionId);
        getOlderStoredProcedure.execute();

        final PreparedStatement minionsStatement = connection.prepareStatement(GET_MINION_NAME_AND_AGE_BY_ID);
        minionsStatement.setInt(1, minionId);

        final ResultSet minions = minionsStatement.executeQuery();
        minions.next();

        System.out.printf(PRINT_MINION_FORMAT,
                minions.getString(Constants.COLUMN_LABEL_NAME),
                minions.getInt(Constants.COLUMN_LABEL_AGE));


    }
}