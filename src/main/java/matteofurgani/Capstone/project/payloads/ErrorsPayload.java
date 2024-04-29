package matteofurgani.Capstone.project.payloads;

import java.util.Date;

public record ErrorsPayload(String message, Date timeStamp) {

    public ErrorsPayload(String message, Date timeStamp){
        this.message = message;
        this.timeStamp = timeStamp;
    }
}