package by.shestopalov.sportplace.service;

import by.shestopalov.sportplace.dto.UserDto;
import by.shestopalov.sportplace.entity.User;
import by.shestopalov.sportplace.exceptions.IncorrectPasswordException;
import by.shestopalov.sportplace.exceptions.UserNameNotFoundException;

import java.util.Optional;

public interface UserService {
    Optional<User> login(UserDto userDto) throws UserNameNotFoundException, IncorrectPasswordException;
    void register(String username, String password, String repeatPassword) throws Exception;
    boolean isAdmin(String username);
    Optional<User> getUserByUsername(String username);
}
