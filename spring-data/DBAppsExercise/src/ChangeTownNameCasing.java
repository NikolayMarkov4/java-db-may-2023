import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChangeTownNameCasing {

    private static final String UPDATE_TOWN_NAME = "update towns t set name = upper(name) where t.country = ?";
    private static final String GET_ALL_TOWN_NAMES_BY_COUNTRY_NAME =
            "select t.name from towns t where t.country = ?";
    private static final String NO_TOWNS_AFFECTED_MESSAGE = "No town names were affected";
    private static final String COUNT_OF_AFFECTED_TOWNS_FORMAT = "%d town names were affected.%n";


    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSqlConnection();

        final String townName = new Scanner(System.in).nextLine();

        final PreparedStatement statement = connection.prepareStatement(UPDATE_TOWN_NAME);
        statement.setString(1, townName);

        final int updatedCount = statement.executeUpdate();

        if (updatedCount == 0) {
            System.out.println(NO_TOWNS_AFFECTED_MESSAGE);
            return;
        }

        System.out.printf(COUNT_OF_AFFECTED_TOWNS_FORMAT, updatedCount);

        final PreparedStatement selectAllTowns = connection.prepareStatement(GET_ALL_TOWN_NAMES_BY_COUNTRY_NAME);
        selectAllTowns.setString(1, townName);

        final ResultSet allTownsResult = selectAllTowns.executeQuery();


        ArrayList<String> towns = new ArrayList<>();

        while (allTownsResult.next()) {
            towns.add(allTownsResult.getString(Constants.COLUMN_LABEL_NAME));
        }

        System.out.println(towns);
    }
}
