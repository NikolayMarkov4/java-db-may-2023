package bg.softuni.gamestoremappingexercise.services.user;

import bg.softuni.gamestoremappingexercise.domain.entities.User;
import bg.softuni.gamestoremappingexercise.domain.models.UserLoginDto;
import bg.softuni.gamestoremappingexercise.domain.models.UserRegisterDto;
import bg.softuni.gamestoremappingexercise.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static bg.softuni.gamestoremappingexercise.constants.ErrorMessages.EMAIL_ALREADY_EXISTS;

@Service
public class UserServiceImplementation implements UserService {
    private User loggedInUser;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String registerUser(String[] args) {
        final int argsLength = args.length;

        final String email = argsLength > 1 ? args[1] : "";
        final String password = argsLength > 2 ? args[2] : "";
        final String confirmPassword = argsLength > 3 ? args[3] : "";
        final String fullName = argsLength > 4 ? args[4] : "";

        UserRegisterDto userRegisterDto;

        try {
            userRegisterDto = new UserRegisterDto(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }

        if (this.userRepository.findFirstByEmail(userRegisterDto.getEmail()).isPresent()) {
            return EMAIL_ALREADY_EXISTS;
        }

        final User user = this.modelMapper.map(userRegisterDto, User.class);

        if (this.userRepository.count() == 0) {
            user.setIsAdmin(true);
        } else {
            user.setIsAdmin(false);
        }

        this.userRepository.saveAndFlush(user);

        return userRegisterDto.successfullyRegisteredUser();
    }

    @Override
    public String loginUser(String[] args) {
        if (this.loggedInUser != null) return "User is already logged.";

        final int argsLength = args.length;

        final String email = argsLength > 1 ? args[1] : "";
        final String password = argsLength > 2 ? args[2] : "";

        final Optional<User> userToBeLogged = this.userRepository.findFirstByEmail(email);

        if (userToBeLogged.isEmpty()) return "Incorrect user.";

        final UserLoginDto userLoginDto = new UserLoginDto(email, password);

        try {
            userLoginDto.validate(userToBeLogged.get().getPassword());
        } catch (IllegalArgumentException exception) {
            return exception.getMessage();
        }

        this.loggedInUser = userToBeLogged.get();

        return userLoginDto.successfullyLogged();
    }

    @Override
    public String logoutUser() {
        if (this.loggedInUser == null) return "No user logged";

        this.loggedInUser = null;

        return "test me logout";
    }

    @Override
    public boolean isLoggedUserAdmin() {
        return this.loggedInUser != null && this.loggedInUser.getIsAdmin();
    }
}
