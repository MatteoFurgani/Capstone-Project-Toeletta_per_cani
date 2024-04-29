package matteofurgani.Capstone.project.users;

import matteofurgani.Capstone.project.exceptions.UserNotActiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sort) {
        return userService.getUsers(page, size, sort);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable int userId) {

        return userService.findById(userId);
    }

    @PutMapping("/activate/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> activateUser(@PathVariable int id) {
        User activatedUser = userService.findByIdAndActivate(id);
        return ResponseEntity.ok(activatedUser);
    }

    @PutMapping("/deactivate/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> deactivateUser(@PathVariable int id) {
        User deactivatedUser = userService.findByIdAndDeactivate(id);
        return ResponseEntity.ok(deactivatedUser);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        userService.findByIdAndDelete(userId);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentUser) {
        if (!currentUser.isActive()){
            throw new UserNotActiveException("User is not active");
        }
        return currentUser;
    }


    @PutMapping("/me")
    public User updateProfile(@RequestBody User user, @AuthenticationPrincipal User currentUser) {
        if (!currentUser.isActive()){
            throw new UserNotActiveException("User is not active");
        }
        return userService.findByIdAndUpdate(currentUser.getId(), user);
    }


    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteprofile(@AuthenticationPrincipal User currentUser) {
        if (!currentUser.isActive()){
            throw new UserNotActiveException("User is not active");
        }
        userService.findByIdAndDelete(currentUser.getId());
        return ResponseEntity.ok("User deleted");
    }

}
