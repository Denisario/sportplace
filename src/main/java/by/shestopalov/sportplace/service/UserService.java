package by.shestopalov.sportplace.service;

import by.shestopalov.sportplace.dto.UserDto;
import by.shestopalov.sportplace.entity.User;
import by.shestopalov.sportplace.exceptions.IncorrectPasswordException;
import by.shestopalov.sportplace.exceptions.UserNameNotFoundException;

import java.util.Optional;

public interface UserService {
    User login(UserDto userDto) throws UserNameNotFoundException, IncorrectPasswordException;
    void register(UserDto userDto) throws Exception;
    boolean isAdmin(String username);
    User getUserByUsername(String username);
}
