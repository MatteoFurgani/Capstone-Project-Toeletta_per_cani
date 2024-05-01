package matteofurgani.Capstone.project.servicesType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewServiceTypeDTO(@NotEmpty(message = "Name is required") String name,
                               @NotNull(message = "Base cost is required") Double baseCost,
                                @NotNull(message = "Duration minutes is required") int durationMinutes) {


}
