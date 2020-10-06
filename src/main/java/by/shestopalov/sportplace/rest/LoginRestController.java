package by.shestopalov.sportplace.rest;

import by.shestopalov.sportplace.dto.UserDto;
import by.shestopalov.sportplace.entity.User;
import by.shestopalov.sportplace.exceptions.IncorrectPasswordException;
import by.shestopalov.sportplace.exceptions.UserNameNotFoundException;
import by.shestopalov.sportplace.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(value = "*")
@RestController
public class LoginRestController {
    private final UserServiceImpl userService;

    @Autowired
    public LoginRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/rest/api/v1/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserDto userDto){
        User user;
        try {
            user = userService.login(userDto);
        } catch (UserNameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IncorrectPasswordException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
