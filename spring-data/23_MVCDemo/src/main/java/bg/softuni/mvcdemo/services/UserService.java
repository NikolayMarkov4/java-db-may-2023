package bg.softuni.mvcdemo.services;

import bg.softuni.mvcdemo.dtos.users.UserRegisterDTO;

public interface UserService {
    boolean register(UserRegisterDTO registerDTO);
}
