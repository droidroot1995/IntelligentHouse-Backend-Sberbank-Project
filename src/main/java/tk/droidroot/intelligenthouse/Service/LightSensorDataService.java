package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.LightSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.LightSensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.LightSensorDataRepository;
import tk.droidroot.intelligenthouse.Repositories.LightSensorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service
public class LightSensorDataService {

    @Autowired
    private LightSensorDataRepository repository;

    @Autowired
    private LightSensorRepository sensorRepository;

    public LightSensorDataDTO findById(Long id) {
        try {
            LightSensorDataEntity lightSensorData = repository.getOne(id);
            LightSensorDataDTO dto = new LightSensorDataDTO();
            dto.setId(lightSensorData.getId());
            dto.setLightSensor(lightSensorData.getLightSensor().getId());
            dto.setData(lightSensorData.getData());
            dto.setDate(lightSensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public LightSensorDataDTO findLastBySensorId(Long id) {
        try {
            List<LightSensorDataEntity> tsde_list = repository.findAllByLightSensorId(id);
            LightSensorDataDTO dto = new LightSensorDataDTO();

            if(tsde_list.size() > 0) {
                LightSensorDataEntity tsde = tsde_list.get(tsde_list.size() - 1);
                dto.setId(tsde.getId());
                dto.setLightSensor(tsde.getLightSensor().getId());
                dto.setData(tsde.getData());
                dto.setDate(tsde.getDate());
            }
            else {
                dto.setId((long)0);
                dto.setLightSensor(id);
                dto.setData(0.0);
                dto.setDate("-");
            }

            return dto;
        }

        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public LightSensorDataEntity create(LightSensorDataDTO lightSensorDataDTO){
        LightSensorDataEntity lightSensorDataEntity = new LightSensorDataEntity();
        lightSensorDataEntity.setLightSensor(sensorRepository.getOne(lightSensorDataDTO.getLightSensor()));
        lightSensorDataEntity.setData(lightSensorDataDTO.getData());
        lightSensorDataEntity.setDate(lightSensorDataDTO.getDate());

        return repository.save(lightSensorDataEntity);
    }

    public LightSensorDataEntity update(LightSensorDataDTO lightSensorDataDTO, Long id){
        return repository.findById(id).map(lightSensorDataEntity -> {
            lightSensorDataEntity.setLightSensor(sensorRepository.getOne(lightSensorDataDTO.getLightSensor()));
            lightSensorDataEntity.setData(lightSensorDataDTO.getData());
            lightSensorDataEntity.setDate(lightSensorDataDTO.getDate());

            return repository.save(lightSensorDataEntity);
        }).orElseGet(() -> {
            LightSensorDataEntity lightSensorDataEntity = new LightSensorDataEntity();
            lightSensorDataEntity.setId(id);
            lightSensorDataEntity.setLightSensor(sensorRepository.getOne(lightSensorDataDTO.getLightSensor()));
            lightSensorDataEntity.setData(lightSensorDataDTO.getData());
            lightSensorDataEntity.setDate(lightSensorDataDTO.getDate());

            return repository.save(lightSensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
