package matteofurgani.Capstone.project.services;

import matteofurgani.Capstone.project.exceptions.UnauthorizedException;
import matteofurgani.Capstone.project.security.JWTTools;
import matteofurgani.Capstone.project.users.User;
import matteofurgani.Capstone.project.users.UserLoginDTO;
import matteofurgani.Capstone.project.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload){

        User user = this.userService.findByEmail(payload.email());

        if(bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Invalid credentials");
        }
    }
}
