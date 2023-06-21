import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge {

    private static final String GET_MINION_BY_ID = "SELECT * FROM minions WHERE id IN ";
    private static final String GET_ALL_MINIONS = "SELECT * FROM minions";

    private static final String COLUMN_LABEL_ID = "id";
    private static final String PRINT_UPDATED_MINIONS_FORMAT = "%d %s %d%n";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSqlConnection();
        final Scanner scanner = new Scanner(System.in);

        final String ids = Arrays.toString(scanner.nextLine().split(" "))
                .replaceAll("\\[", "(")
                .replaceAll("]", ")");

        final PreparedStatement minionsStatement = connection.prepareStatement(GET_MINION_BY_ID + ids);

        final ResultSet minions = minionsStatement.executeQuery();

        while (minions.next()) {
            final String name = minions.getString(Constants.COLUMN_LABEL_NAME).toLowerCase();
            final int age = minions.getInt(Constants.COLUMN_LABEL_AGE) + 1;

            minions.updateString(Constants.COLUMN_LABEL_NAME, name);
            minions.updateInt(Constants.COLUMN_LABEL_AGE, age);

            minions.updateRow();
        }

        final PreparedStatement allMinionsStatement = connection.prepareStatement(GET_ALL_MINIONS);

        final ResultSet allMinions = allMinionsStatement.executeQuery(GET_ALL_MINIONS);

        while (allMinions.next()) {
            final int minionId = allMinions.getInt(COLUMN_LABEL_ID);
            final String minionName = allMinions.getString(Constants.COLUMN_LABEL_NAME);
            final int minionAge = allMinions.getInt(Constants.COLUMN_LABEL_AGE);

            System.out.printf(PRINT_UPDATED_MINIONS_FORMAT, minionId, minionName, minionAge);
        }
    }
}

