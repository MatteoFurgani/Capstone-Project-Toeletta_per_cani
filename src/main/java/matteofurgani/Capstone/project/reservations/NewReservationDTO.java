package matteofurgani.Capstone.project.reservations;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

public record NewReservationDTO(@NotNull(message = "Date is required") LocalDate date,
                                @NotNull(message = "Time is required") LocalTime time,
                                @NotEmpty(message = "Service is required") String serviceType,
                                @NotNull(message = "Pet is required") Integer petInfoId
) {}