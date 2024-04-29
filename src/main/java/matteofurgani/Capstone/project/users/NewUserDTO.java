package matteofurgani.Capstone.project.users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(@NotEmpty(message = "First name is mandatory!")
                         @Size(min = 3, max = 15, message = "The name must have a minimum of 3 characters and a maximum of 15")
                         String firstName,
                         @NotEmpty(message = "Last name is mandatory!")
                         @Size(min = 3, max = 15, message = "The last name must have a minimum of 3 characters and a maximum of 15")
                         String lastName,
                         @NotEmpty(message = "Email is mandatory!")
                         String email,
                         @NotEmpty(message = "Password is mandatory!")
                         @Size(min = 3, max = 15, message = "The password must have a minimum of 3 characters and a maximum of 15")
                         String password,
                         @NotEmpty(message = "Phone number is mandatory!")
                         String phone) {
}
