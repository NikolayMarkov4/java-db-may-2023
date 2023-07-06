package bg.softuni.gamestoremappingexercise.domain.models;

import lombok.Getter;
import lombok.Setter;

import static bg.softuni.gamestoremappingexercise.constants.ErrorMessages.PASS_MISS_MATCH;

@Getter
@Setter
public class UserLoginDto {
    private String email;
    private String password;

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void validate(String realPassword) {
        if (!this.password.equals(realPassword)) {
            throw new IllegalArgumentException(PASS_MISS_MATCH);
        }
    }

    public String successfullyLogged() {
        return "User " + this.email + " successfully logged out";
    }

}
