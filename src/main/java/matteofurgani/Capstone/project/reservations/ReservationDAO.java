package matteofurgani.Capstone.project.reservations;

import matteofurgani.Capstone.project.pets.PetInfo;
import matteofurgani.Capstone.project.servicesType.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByDateAndTime(LocalDate date, LocalTime time);

}

