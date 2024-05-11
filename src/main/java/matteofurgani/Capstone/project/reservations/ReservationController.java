package matteofurgani.Capstone.project.reservations;

import matteofurgani.Capstone.project.exceptions.BadRequestException;
import matteofurgani.Capstone.project.exceptions.InvalidDateException;
import matteofurgani.Capstone.project.exceptions.UserNotActiveException;
import matteofurgani.Capstone.project.petsInfo.PetInfoService;
import matteofurgani.Capstone.project.servicesType.ServiceTypeService;
import matteofurgani.Capstone.project.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService rs;


    @Autowired
    private ServiceTypeService typeService;

    @Autowired
    private PetInfoService petService;


    @PostMapping("/me/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(@AuthenticationPrincipal User user, @RequestBody @Validated NewReservationDTO body, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        try {
            Reservation reservation = rs.save(body, user.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(new NewReservationRespDTO(reservation.getId()));
        } catch (InvalidDateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/me/show")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation show(@AuthenticationPrincipal User user, @RequestBody @Validated NewReservationDTO body, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return rs.show(body, user.getId());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Reservation> getReservations(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sort){
        return rs.getReservation(page, size, sort);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Reservation findById(@PathVariable int id){
        return rs.findById(id);
    }

    @PutMapping("/{reservationId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation findByIdAndUpdate(@PathVariable int reservationId, @RequestBody NewReservationDTO body) {
        try{
            rs.findByIdAndUpdate(reservationId, body);
            return new ResponseEntity<Reservation>(HttpStatus.OK).getBody();
        } catch (UserNotActiveException e){
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST).getBody();
        }
    }

    @DeleteMapping("/{reservationId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int reservationId) {
        rs.findByIdAndDelete(reservationId);
    }

    @GetMapping("/me/{date}/{time}")
    public Reservation getReservation(@AuthenticationPrincipal Reservation currentReservation) {
       return rs.findByDateAndTime(currentReservation.getDate(), currentReservation.getTime());
    }

    @PutMapping("/me/update/{date}/{time}")
    public Reservation updateReservation (@AuthenticationPrincipal Reservation currentReservation, @RequestBody NewReservationDTO reservation) {
        try{
            rs.findByDateAndTimeAndUpdate(currentReservation.getDate(), currentReservation.getTime(), reservation);
            return new ResponseEntity<Reservation>(HttpStatus.OK).getBody();
        } catch (UserNotActiveException e) {
            return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST).getBody();
        }
    }

    @DeleteMapping("/me/delete/{date}/{time}")
    public void deleteReservation(@AuthenticationPrincipal Reservation currentReservation) {
        rs.findByDateAndTimeAndDelete(currentReservation.getDate(), currentReservation.getTime());
    }



}


