package by.shestopalov.sportplace.service.impl;

import by.shestopalov.sportplace.aspect.Loggable;
import by.shestopalov.sportplace.config.Mapper;
import by.shestopalov.sportplace.dto.UserDto;
import by.shestopalov.sportplace.entity.User;
import by.shestopalov.sportplace.exceptions.IncorrectPasswordException;
import by.shestopalov.sportplace.exceptions.UserNameNotFoundException;
import by.shestopalov.sportplace.repository.RoleRepository;
import by.shestopalov.sportplace.repository.UserRepository;
import by.shestopalov.sportplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Loggable
    public Optional<User> login(UserDto userDto) throws UserNameNotFoundException, IncorrectPasswordException {
        User user = Mapper.map(userDto, User.class);

        user.setUsername(user.getUsername().toLowerCase());
        User possibleUser = userRepository
                .findUserByUsername(userDto
                        .getUsername())
                .orElseThrow(()->new UserNameNotFoundException("User not found"));

        if(!possibleUser
                .getPassword()
                .equals(userDto
                        .getPassword())) throw new IncorrectPasswordException("Incorrect password");

        return Optional.of(user);
    }

    @Override
    @Loggable
    public void register(String username, String password, String repeatPassword) throws Exception {
        if(userRepository
                .findUserByUsername(username)
                .isPresent()) throw new Exception("User has already registered");

        if(!password.equals(repeatPassword)){
            throw new Exception("Passwords are not equal");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        user.setRole(roleRepository.getRoleByName("USER"));

        userRepository.save(user);
    }

    @Override
    @Loggable
    public boolean isAdmin(String username) {
        return userRepository.findUserByUsername(username).get().getRole().getName().equals("ADMIN");
    }

    @Override
    @Loggable
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
