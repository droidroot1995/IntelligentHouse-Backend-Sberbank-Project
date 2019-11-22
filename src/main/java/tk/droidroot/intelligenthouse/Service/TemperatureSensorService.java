package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.TemperatureSensorDTO;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorEntity;
import tk.droidroot.intelligenthouse.Repositories.TemperatureSensorRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class TemperatureSensorService {

    @Autowired
    private TemperatureSensorRepository repository;

    public TemperatureSensorDTO findById(Long id) {
        try {
            TemperatureSensorEntity temperatureSensor = repository.getOne(id);
            TemperatureSensorDTO dto = new TemperatureSensorDTO();
            dto.setId(temperatureSensor.getId());
            dto.setName(temperatureSensor.getName());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public TemperatureSensorEntity create(TemperatureSensorDTO temperatureSensorDTO){
        TemperatureSensorEntity temperatureSensorEntity = new TemperatureSensorEntity();
        temperatureSensorEntity.setName(temperatureSensorDTO.getName());

        return repository.save(temperatureSensorEntity);
    }

    public TemperatureSensorEntity update(TemperatureSensorDTO temperatureSensorDTO, Long id){
        return repository.findById(id).map(temperatureSensorEntity -> {
            temperatureSensorEntity.setName(temperatureSensorDTO.getName());

            return repository.save(temperatureSensorEntity);
        }).orElseGet(() -> {
            TemperatureSensorEntity temperatureSensorEntity = new TemperatureSensorEntity();
            temperatureSensorEntity.setId(id);
            temperatureSensorEntity.setName(temperatureSensorDTO.getName());

            return repository.save(temperatureSensorEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
