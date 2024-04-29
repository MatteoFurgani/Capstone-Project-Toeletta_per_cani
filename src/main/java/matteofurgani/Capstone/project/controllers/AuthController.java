package matteofurgani.Capstone.project.controllers;

import matteofurgani.Capstone.project.exceptions.BadRequestException;
import matteofurgani.Capstone.project.services.AuthService;
import matteofurgani.Capstone.project.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserLoginRespDTO login(@RequestBody UserLoginDTO payload){

        return new UserLoginRespDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserRespDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) throws IOException {

        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewUserRespDTO(this.userService.save(body).getId());
    }
}
