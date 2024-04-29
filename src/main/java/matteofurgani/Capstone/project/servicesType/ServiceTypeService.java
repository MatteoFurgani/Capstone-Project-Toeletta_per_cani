package matteofurgani.Capstone.project.servicesType;

import matteofurgani.Capstone.project.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceTypeService {

    @Autowired
    private ServiceTypeDAO sd;

    public ServiceType findById(String name){
        Optional<ServiceType> serviceType = sd.findByName(name);
        if (serviceType.isPresent()) {
            return serviceType.get();
        } else {
            throw new NotFoundException("ServiceType not found for name: " + name);
        }
    }
}
