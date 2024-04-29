package matteofurgani.Capstone.project.users;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import matteofurgani.Capstone.project.reservations.Reservation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({"password", "authorities", "accountNonExpired", "credentialsNonExpired", "accountNonLocked", "enabled"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role roleName;
    private boolean isActive;

   @ManyToMany
    @JoinTable(
            name = "user_reservation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    @JsonIgnore
    private List<Reservation> reservations;

   public User (String firstName, String lastName, String email, String password, String phone) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.password = password;
       this.phone = phone;
       this.roleName = Role.USER;
       this.isActive = true;
   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.roleName.name()));
    }

    @Override
    public String getUsername(){
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
