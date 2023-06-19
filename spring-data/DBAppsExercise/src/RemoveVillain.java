import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {
    private static final String GET_VILLAIN_NAME_BY_ID =
            "select name from minions_db.villains where id = ?";
    private static final String GET_COUNT_MINIONS_BY_VILLAIN_ID =
            "select count(*) m_count from minions_db.minions_villains where villain_id = ?";
    private static final String DELETE_MINIONS_VILLAINS_BY_VILLAIN_ID =
            "delete from minions_db.minions_villains where villain_id = ?";
    private static final String DELETE_VILLAIN_BY_ID =
            "delete from minions_db.villains where id = ?";

    private static final String PRINT_RELEASED_MINIONS_COUNT_FORMAT = "%d minions released";
    private static final String PRINT_VILLAIN_DELETE_FORMAT = "%s was deleted";
    private static final String PRINT_NO_SUCH_VILLAIN_FOUND = "No such villain was found";

    public static void main(String[] args) throws SQLException {
        final Connection sqlConnection = Utils.getSqlConnection();

        final int villainId = new Scanner(System.in).nextInt();

        final PreparedStatement getVillainStatement = sqlConnection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        getVillainStatement.setInt(1, villainId);

        final ResultSet villainResultSet = getVillainStatement.executeQuery();

        if (!villainResultSet.next()) {
            System.out.println(PRINT_NO_SUCH_VILLAIN_FOUND);
            sqlConnection.close();
            return;
        }

        final String villainName = villainResultSet.getString(1);

        final PreparedStatement minionsCountStatement = sqlConnection.prepareStatement(GET_COUNT_MINIONS_BY_VILLAIN_ID);
        minionsCountStatement.setInt(1, villainId);

        final ResultSet minionsCountResultSet = minionsCountStatement.executeQuery();
        minionsCountResultSet.next();

        int countOfMinionsReleased = minionsCountResultSet.getInt(1);

        sqlConnection.setAutoCommit(false);

        try (PreparedStatement releaseMinionsStatement =
                     sqlConnection.prepareStatement(DELETE_MINIONS_VILLAINS_BY_VILLAIN_ID);
             PreparedStatement deleteVillainStatement =
                     sqlConnection.prepareStatement(DELETE_VILLAIN_BY_ID)) {

            releaseMinionsStatement.setInt(1, villainId);
            releaseMinionsStatement.executeUpdate();

            deleteVillainStatement.setInt(1, villainId);
            deleteVillainStatement.executeUpdate();

            sqlConnection.commit();

            System.out.printf(PRINT_VILLAIN_DELETE_FORMAT, villainName);
            System.out.println();
            System.out.printf(PRINT_RELEASED_MINIONS_COUNT_FORMAT, countOfMinionsReleased);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

            sqlConnection.rollback();
        }
    }
}
