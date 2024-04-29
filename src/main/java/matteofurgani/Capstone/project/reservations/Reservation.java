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
@AllArgsConstructor
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

}
