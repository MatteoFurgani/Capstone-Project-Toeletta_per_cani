package matteofurgani.Capstone.project.pets;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetInfoDAO extends JpaRepository<PetInfo, Integer> {

    boolean existsById(int id);
    Optional<PetInfo> findById(int id);
}
