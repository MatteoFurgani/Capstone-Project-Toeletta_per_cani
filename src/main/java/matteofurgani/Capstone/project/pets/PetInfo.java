package matteofurgani.Capstone.project.pets;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pets_info")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Size size;
    @Enumerated(EnumType.STRING)
    private HairType hairType;

    public PetInfo(Size size, HairType hairType) {
        this.size = size;
        this.hairType = hairType;
    }
}
