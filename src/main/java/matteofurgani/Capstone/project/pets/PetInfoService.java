package matteofurgani.Capstone.project.pets;

import matteofurgani.Capstone.project.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    public Page<PetInfo> getPetInfo(int page, int size, String sort) {
        if(size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return petInfoDAO.findAll(pageable);
    }

    public PetInfo findById(int id){

        return petInfoDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public PetInfo findByIdAndUpdate(int id, PetInfo body) {
        PetInfo found = this.findById(id);

        if(body.getSize() != null){
            found.setSize(body.getSize());
        }
        if (body.getHairType() != null) {
            found.setHairType(body.getHairType());
        }
        return petInfoDAO.save(found);
    }

    public void findByIdAndDelete(int id) {
        PetInfo found = this.findById(id);
        petInfoDAO.delete(found);
    }
}
