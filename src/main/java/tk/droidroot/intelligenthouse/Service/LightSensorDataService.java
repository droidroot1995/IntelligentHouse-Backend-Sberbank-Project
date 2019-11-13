package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import tk.droidroot.intelligenthouse.DTO.LightSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.LightSensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.LightSensorDataRepository;

import javax.persistence.EntityNotFoundException;

public class LightSensorDataService {

    @Autowired
    private LightSensorDataRepository repository;

    public LightSensorDataDTO findById(Long id) {
        try {
            LightSensorDataEntity LightSensorData = repository.getOne(id);
            LightSensorDataDTO dto = new LightSensorDataDTO();
            dto.setId(LightSensorData.getId());
            dto.setData(LightSensorData.getData());
            dto.setDate(LightSensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public LightSensorDataEntity create(LightSensorDataDTO LightSensorDataDTO){
        LightSensorDataEntity LightSensorDataEntity = new LightSensorDataEntity();
        LightSensorDataEntity.setData(LightSensorDataDTO.getData());
        LightSensorDataEntity.setDate(LightSensorDataDTO.getDate());

        return repository.save(LightSensorDataEntity);
    }

    public LightSensorDataEntity update(LightSensorDataDTO LightSensorDataDTO, Long id){
        return repository.findById(id).map(LightSensorDataEntity -> {
            LightSensorDataEntity.setData(LightSensorDataDTO.getData());
            LightSensorDataEntity.setDate(LightSensorDataDTO.getDate());

            return repository.save(LightSensorDataEntity);
        }).orElseGet(() -> {
            LightSensorDataEntity LightSensorDataEntity = new LightSensorDataEntity();
            LightSensorDataEntity.setId(id);
            LightSensorDataEntity.setData(LightSensorDataDTO.getData());
            LightSensorDataEntity.setDate(LightSensorDataDTO.getDate());

            return repository.save(LightSensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
