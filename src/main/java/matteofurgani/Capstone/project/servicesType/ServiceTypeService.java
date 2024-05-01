package matteofurgani.Capstone.project.servicesType;

import matteofurgani.Capstone.project.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public ServiceType findById(int id) {
        return sd.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public ServiceType findByName(String name) {
        return sd.findByName(name).orElseThrow(() -> new NotFoundException("ServiceType not found for name: " + name));
    }

}
