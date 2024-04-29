package matteofurgani.Capstone.project.reservations;

import matteofurgani.Capstone.project.pets.PetInfo;
import matteofurgani.Capstone.project.pets.PetInfoService;
import matteofurgani.Capstone.project.servicesType.ServiceType;
import matteofurgani.Capstone.project.servicesType.ServiceTypeService;
import matteofurgani.Capstone.project.users.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReservationService {

    @Autowired
    private ReservationDAO rd;

    @Autowired
    private PetInfoService petService;

    @Autowired
    private ServiceTypeService typeService;

  /* public Reservation save(NewReservationDTO body) throws IOException{

       ServiceType serviceType = typeService.findById(String.valueOf(body.serviceId()));
       PetInfo petInfo = petService.findById(body.petinfoId());

       Reservation reservation = new Reservation(body.date(), body.time(), serviceType, petInfo);
       reservation.setDate(body.date());
       reservation.setTime(body.time());
       reservation.setServiceName(serviceType);
       reservation.setPetInfo(petInfo);
       return rd.save(reservation);
   }
*/

}
