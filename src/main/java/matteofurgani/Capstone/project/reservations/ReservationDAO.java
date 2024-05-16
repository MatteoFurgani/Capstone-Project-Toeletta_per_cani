package matteofurgani.Capstone.project.reservations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByDateAndTime(LocalDate date, LocalTime time);

    Page<Reservation> findByUserId(int userId, Pageable pageable);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.date = :date AND (r.time > :startTime AND r.time < :endTime)")
    long countByDateAndTimeRange(LocalDate date, LocalTime startTime, LocalTime endTime);

}

