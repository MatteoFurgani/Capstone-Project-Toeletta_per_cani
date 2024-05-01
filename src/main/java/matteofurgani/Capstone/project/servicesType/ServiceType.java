package matteofurgani.Capstone.project.servicesType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import matteofurgani.Capstone.project.reservations.Reservation;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services_type")
@Getter
@Setter
@NoArgsConstructor
public class ServiceType {
    @Id
    private String name;
    private double baseCost;
    private int durationMinutes;
    @OneToMany(mappedBy = "serviceType")
    @JsonIgnore
    private List<Reservation> reservations;



    public ServiceType(String name, double baseCost, int durationMinutes) {
        this.name = name;
        this.baseCost = baseCost;
        this.durationMinutes = durationMinutes;
        //this.reservations = new ArrayList<>();
    }

    public ServiceType(String name) {
        this.name = name;
    }



}
