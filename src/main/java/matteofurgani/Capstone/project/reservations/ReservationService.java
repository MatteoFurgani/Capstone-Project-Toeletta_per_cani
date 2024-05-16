package matteofurgani.Capstone.project.reservations;

import matteofurgani.Capstone.project.exceptions.InvalidDateException;
import matteofurgani.Capstone.project.exceptions.NotFoundException;
import matteofurgani.Capstone.project.petsInfo.PetInfo;
import matteofurgani.Capstone.project.petsInfo.PetInfoService;
import matteofurgani.Capstone.project.servicesType.ServiceType;
import matteofurgani.Capstone.project.servicesType.ServiceTypeService;
import matteofurgani.Capstone.project.users.User;
import matteofurgani.Capstone.project.users.UserDAO;
import matteofurgani.Capstone.project.users.UserService;
import matteofurgani.Capstone.project.utility.costs.CostGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ReservationService {

    @Autowired
    private ReservationDAO rd;


    @Autowired
    private PetInfoService petService;

    @Autowired
    private ServiceTypeService typeService;

    @Autowired
    private UserService userService;

    public Reservation show(NewReservationDTO body, int userId) throws IOException{

        boolean isValidDate = checkDate(body.date());
        if(!isValidDate){
            throw new InvalidDateException();
        }

        boolean reservationAlreadyExists = isReservationExists(body.date(), body.time());
        if(reservationAlreadyExists){
            throw  new InvalidDateException("Già esiste una prenotazione per questa data e ora");
        }

        ServiceType serviceType = typeService.findByName(String.valueOf(body.serviceType()));
        PetInfo petInfo = petService.findById(body.petInfoId());
        User user = userService.findById(userId);

        Double cost = serviceType.getBaseCost();
        String petSize = String.valueOf(petInfo.getSize());
        String petHair = String.valueOf(petInfo.getHairType());

        CostGenerator cg = new CostGenerator();
        String finalCost = cg.generateProperCost(petHair,petSize, cost);

        Reservation reservation = new Reservation(body.date(), body.time(), serviceType, petInfo, user);
        reservation.setDate(body.date());
        reservation.setTime(body.time());
        reservation.setServiceType(serviceType);
        reservation.setPetInfo(petInfo);
        reservation.setCost(finalCost);
        reservation.setUser(user);

        return reservation;

       /* return rd.save(reservation);*/
    }

    public Reservation save(NewReservationDTO body, int id) throws IOException {
        return rd.save(show(body, id));
    }

    public boolean isReservationExists(LocalDate date, LocalTime time){
        LocalTime prevTime = time.minusHours(1);
        LocalTime nextTime = time.plusHours(1);

        long count = rd.countByDateAndTimeRange(date, prevTime, nextTime);
        return count > 0;
    }

    private boolean checkDate(LocalDate date){
        return date.isAfter(LocalDate.now());
    }

    public Page<Reservation> getReservation(int page, int size, String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return rd.findAll(pageable);
    }

    public Page<Reservation> getMyReservations(int userId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return rd.findByUserId(userId, pageable);
    }

    public Reservation findById(int id) {

        return rd.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Reservation findByDateAndTime(LocalDate date, LocalTime time){
        return rd.findByDateAndTime(date, time).
                orElseThrow(() -> new NotFoundException("Reservation not found for date: " + date + " and time: " + time));
    }


    public Reservation findByIdAndUpdate(int id, NewReservationDTO body){

        double cost = 0;
        String petSize = null;
        String petHair = null;

        Reservation found = this.findById(id);

        found.setDate(body.date());
        found.setTime(body.time());

        // Trova il ServiceType corrispondente
        ServiceType serviceType = typeService.findByName(body.serviceType());
        found.setServiceType(serviceType);
        cost = serviceType.getBaseCost();

        // Trova il PetInfo corrispondente
        PetInfo petInfo = petService.findById(body.petInfoId());
        found.setPetInfo(petInfo);

        // Calcola il costo basato sul ServiceType e sul PetInfo

        petSize = String.valueOf(petInfo.getSize());
        petHair = String.valueOf(petInfo.getHairType());

        CostGenerator cg = new CostGenerator();
        String finalCost = cg.generateProperCost(petHair, petSize, cost);
        found.setCost(finalCost);

        return rd.save(found);

    }


    /*public Reservation findByDateAndTimeAndUpdate(LocalDate date, LocalTime time, Reservation body) {
        Reservation found = this.findByDateAndTime(date, time);
        found.setDate(body.getDate());
        found.setTime(body.getTime());
        found.setServiceType(body.getServiceType());
        found.setPetInfo(body.getPetInfo());
        return rd.save(found);
    }*/

    public Reservation findByDateAndTimeAndUpdate(LocalDate date, LocalTime time, NewReservationDTO body) {
        Reservation found = this.findByDateAndTime(date, time);

        // Aggiorna la data e l'ora della prenotazione con i valori forniti nel corpo
        found.setDate(body.date());
        found.setTime(body.time());

        // Trova il ServiceType corrispondente
        ServiceType serviceType = typeService.findByName(body.serviceType());
        found.setServiceType(serviceType);
        double cost = serviceType.getBaseCost();

        // Trova il PetInfo corrispondente
        PetInfo petInfo = petService.findById(body.petInfoId());
        found.setPetInfo(petInfo);

        // Calcola il costo basato sul ServiceType e sul PetInfo
        String petSize = String.valueOf(petInfo.getSize());
        String petHair = String.valueOf(petInfo.getHairType());
        CostGenerator cg = new CostGenerator();
        String finalCost = cg.generateProperCost(petHair, petSize, cost);
        found.setCost(finalCost);

        return rd.save(found);
    }

    public void findByIdAndDelete(int id) {
        Reservation reservation = rd.findById(id).orElseThrow(() -> new NotFoundException(id));
        rd.delete(reservation);
    }

    public void findByDateAndTimeAndDelete(LocalDate date, LocalTime time) {
        Reservation reservation = rd.findByDateAndTime(date, time).orElseThrow(() -> new NotFoundException("Reservation not found for date: " + date + " and time: " + time));
        rd.delete(reservation);
    }

}
