package matteofurgani.Capstone.project.payloads;

import java.util.Date;
import java.util.List;

public record ErrorsRespDTO(String message, Date timeStamp, List<String> errorsList) {
}
