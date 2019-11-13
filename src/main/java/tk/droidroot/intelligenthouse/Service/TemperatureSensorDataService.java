package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import tk.droidroot.intelligenthouse.DTO.TemperatureSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.TemperatureSensorDataRepository;

import javax.persistence.EntityNotFoundException;

public class TemperatureSensorDataService {

    @Autowired
    private TemperatureSensorDataRepository repository;

    public TemperatureSensorDataDTO findById(Long id) {
        try {
            TemperatureSensorDataEntity TemperatureSensorData = repository.getOne(id);
            TemperatureSensorDataDTO dto = new TemperatureSensorDataDTO();
            dto.setId(TemperatureSensorData.getId());
            dto.setData(TemperatureSensorData.getData());
            dto.setDate(TemperatureSensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public TemperatureSensorDataEntity create(TemperatureSensorDataDTO TemperatureSensorDataDTO){
        TemperatureSensorDataEntity TemperatureSensorDataEntity = new TemperatureSensorDataEntity();
        TemperatureSensorDataEntity.setData(TemperatureSensorDataDTO.getData());
        TemperatureSensorDataEntity.setDate(TemperatureSensorDataDTO.getDate());

        return repository.save(TemperatureSensorDataEntity);
    }

    public TemperatureSensorDataEntity update(TemperatureSensorDataDTO TemperatureSensorDataDTO, Long id){
        return repository.findById(id).map(TemperatureSensorDataEntity -> {
            TemperatureSensorDataEntity.setData(TemperatureSensorDataDTO.getData());
            TemperatureSensorDataEntity.setDate(TemperatureSensorDataDTO.getDate());

            return repository.save(TemperatureSensorDataEntity);
        }).orElseGet(() -> {
            TemperatureSensorDataEntity TemperatureSensorDataEntity = new TemperatureSensorDataEntity();
            TemperatureSensorDataEntity.setId(id);
            TemperatureSensorDataEntity.setData(TemperatureSensorDataDTO.getData());
            TemperatureSensorDataEntity.setDate(TemperatureSensorDataDTO.getDate());

            return repository.save(TemperatureSensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
