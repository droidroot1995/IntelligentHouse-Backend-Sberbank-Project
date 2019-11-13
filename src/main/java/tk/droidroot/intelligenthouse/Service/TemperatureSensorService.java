package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import tk.droidroot.intelligenthouse.DTO.TemperatureSensorDTO;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorEntity;
import tk.droidroot.intelligenthouse.Repositories.TemperatureSensorRepository;

import javax.persistence.EntityNotFoundException;

public class TemperatureSensorService {

    @Autowired
    private TemperatureSensorRepository repository;

    public TemperatureSensorDTO findById(Long id) {
        try {
            TemperatureSensorEntity TemperatureSensor = repository.getOne(id);
            TemperatureSensorDTO dto = new TemperatureSensorDTO();
            dto.setId(TemperatureSensor.getId());
            dto.setName(TemperatureSensor.getName());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public TemperatureSensorEntity create(TemperatureSensorDTO TemperatureSensorDTO){
        TemperatureSensorEntity TemperatureSensorEntity = new TemperatureSensorEntity();
        TemperatureSensorEntity.setName(TemperatureSensorDTO.getName());

        return repository.save(TemperatureSensorEntity);
    }

    public TemperatureSensorEntity update(TemperatureSensorDTO TemperatureSensorDTO, Long id){
        return repository.findById(id).map(TemperatureSensorEntity -> {
            TemperatureSensorEntity.setName(TemperatureSensorDTO.getName());

            return repository.save(TemperatureSensorEntity);
        }).orElseGet(() -> {
            TemperatureSensorEntity TemperatureSensorEntity = new TemperatureSensorEntity();
            TemperatureSensorEntity.setId(id);
            TemperatureSensorEntity.setName(TemperatureSensorDTO.getName());

            return repository.save(TemperatureSensorEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
