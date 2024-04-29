package matteofurgani.Capstone.project.reservations;

import jakarta.validation.constraints.NotNull;
import matteofurgani.Capstone.project.pets.PetInfo;
import matteofurgani.Capstone.project.servicesType.ServiceType;

import java.time.LocalDate;
import java.time.LocalTime;

public record NewReservationDTO(@NotNull(message = "Date is required")
                                LocalDate date,
                                @NotNull(message = "Time is required")
                                LocalTime time,
                                @NotNull(message = "Service is required")
                                int serviceId,
                                @NotNull(message = "Pet is required")
                                int petinfoId) {
}
