package matteofurgani.Capstone.project.servicesType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import matteofurgani.Capstone.project.reservations.Reservation;

import java.util.List;

@Entity
@Table(name = "services_type")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceType {
    @Id
    private String name;
    private double baseCost;
    private int durationMinutes;
    @OneToMany(mappedBy = "serviceName")
    private List<Reservation> reservations;
}
