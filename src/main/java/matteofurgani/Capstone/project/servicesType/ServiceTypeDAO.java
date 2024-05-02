package matteofurgani.Capstone.project.servicesType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceTypeDAO extends JpaRepository<ServiceType, String> {

    boolean existsByName(String name);
    Optional<ServiceType> findByName(String name);
}
