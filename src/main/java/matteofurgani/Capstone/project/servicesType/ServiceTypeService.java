package matteofurgani.Capstone.project.servicesType;

import matteofurgani.Capstone.project.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceTypeService {

    @Autowired
    private ServiceTypeDAO sd;

    public ServiceType save(ServiceType body) throws IOException {
        ServiceType serviceType = new ServiceType(body.getName(), body.getBaseCost(), body.getDurationMinutes());
        serviceType.setName(body.getName());
        serviceType.setBaseCost(body.getBaseCost());
        serviceType.setDurationMinutes(body.getDurationMinutes());
        return sd.save(serviceType);
    }

    public Page<ServiceType> findService(int page, int size, String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return sd.findAll(pageable);
    }

    public ServiceType findByName(String name) {
        return sd.findByName(name).orElseThrow(() -> new NotFoundException("ServiceType not found for name: " + name));
    }

    public ServiceType findByNameAndUpdate(String name, ServiceType body) {

        ServiceType found = sd.findByName(name)
                .orElseThrow(() -> new NotFoundException("ServiceType not found with name: " + name));

        if (body.getBaseCost() != 0.0) {
            found.setBaseCost(body.getBaseCost());
        }
        if (body.getDurationMinutes() != 0) {
            found.setDurationMinutes(body.getDurationMinutes());
        }
        return sd.save(found);
    }

    public void findByNameAndDelete(String name){
        ServiceType found = this.findByName(name);
        sd.delete(found);
    }

}
