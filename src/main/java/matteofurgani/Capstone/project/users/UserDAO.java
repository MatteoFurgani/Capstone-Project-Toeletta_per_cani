package matteofurgani.Capstone.project.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO  extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
