package matteofurgani.Capstone.project.reservations;

import jakarta.persistence.*;
import lombok.*;
import matteofurgani.Capstone.project.pets.PetInfo;
import matteofurgani.Capstone.project.servicesType.ServiceType;


import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "reservations")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private LocalTime time;
    private int cost;
    @ManyToOne
    @JoinColumn(name = "name")
    private ServiceType serviceName;
    @OneToOne
    private PetInfo petInfo;

    public Reservation(LocalDate date, LocalTime time, ServiceType serviceName, PetInfo petInfo) {
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.serviceName = serviceName;
        this.petInfo = petInfo;
    }

}
