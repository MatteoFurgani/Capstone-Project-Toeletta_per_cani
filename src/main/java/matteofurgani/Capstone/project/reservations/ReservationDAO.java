package matteofurgani.Capstone.project.reservations;

import matteofurgani.Capstone.project.pets.PetInfo;
import matteofurgani.Capstone.project.servicesType.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, LocalDate> {
    List<Reservation> findByDate(LocalDate date);
}
