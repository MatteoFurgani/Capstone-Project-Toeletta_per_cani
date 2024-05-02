package matteofurgani.Capstone.project.pets;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import matteofurgani.Capstone.project.reservations.Reservation;

import java.util.List;

@Entity
@Table(name = "pets_info")
@Getter
@Setter
@NoArgsConstructor
public class PetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Size size;
    @Enumerated(EnumType.STRING)
    private HairType hairType;
    @OneToMany(mappedBy = "petInfo")
    @JsonIgnore
    private List<Reservation> reservation;


    public PetInfo(Size size, HairType hairType) {
        this.size = size;
        this.hairType = hairType;
    }

}
