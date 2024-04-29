package matteofurgani.Capstone.project.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class PetInfoService {

    @Autowired
    private PetInfoDAO petInfoDAO;

    public PetInfo save(PetInfo body) throws IOException {
        PetInfo pet = new PetInfo(body.getSize(), body.getHairType());
        pet.setSize(body.getSize());
        pet.setHairType(body.getHairType());
        return petInfoDAO.save(pet);
    }

    public PetInfo findById(int id){
        Optional<PetInfo> pet = petInfoDAO.findById(id);
        if (pet.isPresent()) {
            return pet.get();
        } else {
            throw new RuntimeException("No pet found with id: " + id);
        }
    }
}
