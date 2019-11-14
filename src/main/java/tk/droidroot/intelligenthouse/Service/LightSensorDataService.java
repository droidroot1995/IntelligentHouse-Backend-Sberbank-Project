package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.LightSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.LightSensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.LightSensorDataRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class LightSensorDataService {

    @Autowired
    private LightSensorDataRepository repository;

    public LightSensorDataDTO findById(Long id) {
        try {
            LightSensorDataEntity lightSensorData = repository.getOne(id);
            LightSensorDataDTO dto = new LightSensorDataDTO();
            dto.setId(lightSensorData.getId());
            dto.setLightSensor(lightSensorData.getLightSensor());
            dto.setData(lightSensorData.getData());
            dto.setDate(lightSensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public LightSensorDataEntity create(LightSensorDataDTO lightSensorDataDTO){
        LightSensorDataEntity lightSensorDataEntity = new LightSensorDataEntity();
        lightSensorDataEntity.setLightSensor(lightSensorDataDTO.getLightSensor());
        lightSensorDataEntity.setData(lightSensorDataDTO.getData());
        lightSensorDataEntity.setDate(lightSensorDataDTO.getDate());

        return repository.save(lightSensorDataEntity);
    }

    public LightSensorDataEntity update(LightSensorDataDTO lightSensorDataDTO, Long id){
        return repository.findById(id).map(lightSensorDataEntity -> {
            lightSensorDataEntity.setLightSensor(lightSensorDataDTO.getLightSensor());
            lightSensorDataEntity.setData(lightSensorDataDTO.getData());
            lightSensorDataEntity.setDate(lightSensorDataDTO.getDate());

            return repository.save(lightSensorDataEntity);
        }).orElseGet(() -> {
            LightSensorDataEntity lightSensorDataEntity = new LightSensorDataEntity();
            lightSensorDataEntity.setId(id);
            lightSensorDataEntity.setLightSensor(lightSensorDataDTO.getLightSensor());
            lightSensorDataEntity.setData(lightSensorDataDTO.getData());
            lightSensorDataEntity.setDate(lightSensorDataDTO.getDate());

            return repository.save(lightSensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
