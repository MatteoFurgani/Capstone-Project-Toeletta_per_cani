package matteofurgani.Capstone.project.reservations;


import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.*;
import lombok.*;
import matteofurgani.Capstone.project.pets.PetInfo;
import matteofurgani.Capstone.project.servicesType.ServiceType;
import matteofurgani.Capstone.project.users.User;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;


@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private LocalTime time;
    private String cost;
    @ManyToOne
    @JoinColumn(name = "service_type", referencedColumnName = "name")
    private ServiceType serviceType;
    @ManyToOne
    @JoinColumn(name = "pet_info_id", referencedColumnName = "id")
    private PetInfo petInfo;

    public Reservation(LocalDate date, LocalTime time, ServiceType serviceType, PetInfo petInfo) {
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.serviceType = serviceType;
        this.petInfo = petInfo;
    }

}
