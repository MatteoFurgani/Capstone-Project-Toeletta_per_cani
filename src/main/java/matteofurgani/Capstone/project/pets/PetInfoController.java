package matteofurgani.Capstone.project.pets;

import matteofurgani.Capstone.project.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/pets")
public class PetInfoController {

    @Autowired
    private PetInfoService petService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewPetInfoRespDTO save(@RequestBody NewPetInfoDTO body, BindingResult validation) throws IOException {

        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }

        PetInfo pet = new PetInfo(body.size(), body.hairType());
        pet = petService.save(pet);
        return new NewPetInfoRespDTO(pet.getId());
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<PetInfo> getPetInfo(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sort){
        return petService.getPetInfo(page, size, sort);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public PetInfo findById(@PathVariable int id) {
        return petService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public PetInfo findByIdAndUpdate(@PathVariable int id, @RequestBody PetInfo body) {
        return petService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetInfo(@PathVariable int id) {
        petService.findByIdAndDelete(id);
    }
}
