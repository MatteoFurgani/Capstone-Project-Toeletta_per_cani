package matteofurgani.Capstone.project.servicesType;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/services")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService sts;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewServiceTypeRespDTO save(@RequestBody NewServiceTypeDTO body, BindingResult validation) throws IOException {
        if (validation.hasErrors()){
            throw  new BadRequestException(String.valueOf(validation.getAllErrors()));
        }
        ServiceType serviceType = new ServiceType(body.name(), body.baseCost(), body.durationMinutes());
        serviceType = sts.save(serviceType);
        return new NewServiceTypeRespDTO(serviceType.getName());
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<ServiceType> getServices(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "name") String sort){
        return sts.findService(page, size, sort);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ServiceType findByName(@PathVariable String name) {
        return sts.findByName(name);
    }

    @PutMapping("/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ServiceType updateByName(@PathVariable String name, @RequestBody ServiceType body) {
        return sts.findByNameAndUpdate(name, body);
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByName(@PathVariable String name) {
        sts.findByNameAndDelete(name);
    }
}
