package by.shestopalov.sportplace.rest;

import by.shestopalov.sportplace.dto.UserDto;
import by.shestopalov.sportplace.entity.User;
import by.shestopalov.sportplace.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(value = "*")
@RestController
public class RegisterRestController {
    private final UserServiceImpl userService;


    @Autowired
    public RegisterRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/rest/api/v1/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserDto userDto){
        try {

            userService.register(userDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
