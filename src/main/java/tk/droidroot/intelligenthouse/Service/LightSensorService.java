package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import tk.droidroot.intelligenthouse.DTO.LightSensorDTO;
import tk.droidroot.intelligenthouse.Models.LightSensorEntity;
import tk.droidroot.intelligenthouse.Repositories.LightSensorRepository;

import javax.persistence.EntityNotFoundException;

public class LightSensorService {

    @Autowired
    private LightSensorRepository repository;

    public LightSensorDTO findById(Long id) {
        try {
            LightSensorEntity LightSensor = repository.getOne(id);
            LightSensorDTO dto = new LightSensorDTO();
            dto.setId(LightSensor.getId());
            dto.setName(LightSensor.getName());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public LightSensorEntity create(LightSensorDTO LightSensorDTO){
        LightSensorEntity LightSensorEntity = new LightSensorEntity();
        LightSensorEntity.setName(LightSensorDTO.getName());

        return repository.save(LightSensorEntity);
    }

    public LightSensorEntity update(LightSensorDTO LightSensorDTO, Long id){
        return repository.findById(id).map(LightSensorEntity -> {
            LightSensorEntity.setName(LightSensorDTO.getName());

            return repository.save(LightSensorEntity);
        }).orElseGet(() -> {
            LightSensorEntity LightSensorEntity = new LightSensorEntity();
            LightSensorEntity.setId(id);
            LightSensorEntity.setName(LightSensorDTO.getName());

            return repository.save(LightSensorEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
