package entities.constants;

public enum Constants {
    ;
    public static final String ID_COLUM_MISSING_MESSAGE = "Entity missing an Id column";
    public static final String CLASS_MUST_BE_ENTITY_MESSAGE = "Class must be Entity";
    public static final String COMMA_SEPARATOR = ", ";

    public enum Queries {
        ;
        public static final String INSET_QUERY_FORMAT = "INSERT INTO %s (%s) VALUES (%s)";
        
        public static final String UPDATE_QUERY_BY_ID_FORMAT = "UPDATE %s e SET %s WHERE e.id = %d";
       
        public static final String FIND_FIRST_WITH_CONDITION_QUERY = "SELECT * FROM %s %s LIMIT 1";
        
        public static final String FIND_ALL_WITH_CONDITION_QUERY = "SELECT * FROM %s %s";
        
        public static final String FIND_FIRST_QUERY = "SELECT * FROM %s LIMIT 1";
        
        public static final String FIND_ALL_QUERY = "SELECT * FROM %s";
       
        public static final String CREATE_TABLE_QUERY_FORMAT = "CREATE TABLE %s (id INT PRIMARY KEY AUTO_INCREMENT, %s);";
       
        public static final String CREATE_VALUE_FORMAT = "%s %s";

        public static final String UPDATE_VALUE_FORMAT = "%s = %s";

        public static final String WHERE_KEY_WORD = "WHERE ";

        public static final String ADD_COLUMN_FORMAT = "ADD COLUMN %s %s";
        
        public static final String ALTER_TABLE_FORMAT = "ALTER TABLE %s %s";
       
        public static final String DELETE_RECORD_BY_CONDITION_FORMAT = "DELETE FROM %s WHERE %s = %s;";
       
        public static final String GET_ALL_COLUMN_NAMES_BY_TABLE_NAME =
                "SELECT `COLUMN_NAME`\n" +
                        "from information_schema.COLUMNS\n" +
                        "WHERE TABLE_SCHEMA = 'custom-orm-workshop'\n" +
                        "  AND COLUMN_NAME != 'id'\n" +
                        "  AND TABLE_NAME = ?";
    }

    public enum SQLTypes{
        ;
        public static final String INT = "INT";
        public static final String DATE = "DATE";
        public static final String VARCHAR = "VARCHAR(100)";
    }
}
