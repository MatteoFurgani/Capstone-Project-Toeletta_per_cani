package matteofurgani.Capstone.project.users;

import matteofurgani.Capstone.project.exceptions.BadRequestException;
import matteofurgani.Capstone.project.exceptions.NotFoundException;
import matteofurgani.Capstone.project.reservations.Reservation;
import matteofurgani.Capstone.project.reservations.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder bcrypt;

   /* @Autowired
    private ReservationService rs;*/

    public User save(NewUserDTO body) throws IOException{
        userDAO.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + body.email() + " already in use");
                }
        );
        User user = new User(body.firstName(), body.lastName(), body.email(), bcrypt.encode(body.password()), body.phone());
        return userDAO.save(user);
    }

    public Page<User> getUsers(int page, int size, String sort){
        if(size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return userDAO.findAll(pageable);
    }

    public User findById(int id){
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail( String email){
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("User's email " + email + " not found"));
    }

    public User findByIdAndActivate(int id) {
        User found = this.findById(id);
        found.setActive(true);
        return userDAO.save(found);
    }

    public User findByIdAndDeactivate(int id) {
        User found = this.findById(id);
        found.setActive(false);
        return userDAO.save(found);
    }

    public User findByIdAndUpdate(int id, User body) {
        User found = this.findById(id);

        if (body.getEmail() != null) {
            found.setEmail(body.getEmail());
        }
        if (body.getPassword() != null) {
            found.setPassword(body.getPassword());
        }
        if (body.getPhone() != null) {
            found.setPhone(body.getPhone());
        }

        return userDAO.save(found);
    }


    public void findByIdAndDelete(int id) {
        User found = this.findById(id);
        userDAO.delete(found);
    }

    /*public User addUserReservation(int userId, int reservationId) {
        User user = userDAO.findById(userId).orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        Reservation reservation = rs.findById(reservationId);

        user.getReservations().add(reservation);
        userDAO.save(user);
        return userDAO.save(user);
    }*/



}
