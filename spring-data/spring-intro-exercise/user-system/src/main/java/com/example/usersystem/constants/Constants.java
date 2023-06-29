package com.example.usersystem.constants;

public enum Constants {
    ;
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format!";
    public static final String EMAIL_NAME_LENGTH_TOO_SHORT = "Email name length too short!";
    public static final String EMAIL_NAME_LENGTH_TOO_LONG = "Email name length too long!";
    public static final String EMAIL_HOST_LENGTH_TOO_LONG = "Email host length too long!";
    public static final String EMAIL_VALIDATION_REGEX = "^([a-zA-Z0-9][-_.]?)*[a-zA-Z0-9]@([a-zA-Z][-]?)*[a-zA-Z](\\.([a-zA-Z][-]?)*[a-zA-Z])+$";

    public static final String INVALID_PASSWORD_FORMAT = "Invalid password format!";
    public static final String PASSWORD_CANNOT_BE_NULL = "Password cannot be null!";
    public static final String PASSWORD_TOO_SHORT = "Password too short!";
    public static final String PASSWORD_TOO_LONG = "Password too long!";
    public static final String PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER = "Password should contain lowercase letter!";
    public static final String PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER = "Password should contain uppercase letter!";
    public static final String PASSWORD_SHOULD_CONTAIN_DIGIT = "Password should contain digit!";
    public static final String PASSWORD_SHOULD_CONTAIN_SPECIAL_SYMBOL = "Password should contain one of: !@#$%^&*()_+<>?";

    public static final String AGE_TOO_LOW = "Age cannot be less than 1";
    public static final String AGE_TOO_HIGH = "Age cannot be more than 120";
    public static final String USERNAME_INCORRECT_LENGTH = "Username length should be between 4 and 30 symbols";

    public static final String FULL_NAME_AGE_FORMAT = "%s %s - %d years old";
}