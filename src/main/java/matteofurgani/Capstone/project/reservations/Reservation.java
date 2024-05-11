package matteofurgani.Capstone.project.reservations;


import jakarta.persistence.*;
import lombok.*;
import matteofurgani.Capstone.project.petsInfo.PetInfo;
import matteofurgani.Capstone.project.servicesType.ServiceType;
import matteofurgani.Capstone.project.users.User;


import java.time.LocalDate;
import java.time.LocalTime;


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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;



   public Reservation(LocalDate date, LocalTime time, ServiceType serviceType, PetInfo petInfo, User user) {
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.serviceType = serviceType;
        this.petInfo = petInfo;
        this.user = user;
    }

}
