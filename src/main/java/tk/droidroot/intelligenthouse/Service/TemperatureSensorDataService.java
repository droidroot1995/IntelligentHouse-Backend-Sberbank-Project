package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.TemperatureSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.TemperatureSensorDataRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class TemperatureSensorDataService {

    @Autowired
    private TemperatureSensorDataRepository repository;

    public TemperatureSensorDataDTO findById(Long id) {
        try {
            TemperatureSensorDataEntity temperatureSensorData = repository.getOne(id);
            TemperatureSensorDataDTO dto = new TemperatureSensorDataDTO();
            dto.setId(temperatureSensorData.getId());
            dto.setTemperatureSensor(temperatureSensorData.getTemperatureSensor());
            dto.setData(temperatureSensorData.getData());
            dto.setDate(temperatureSensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public TemperatureSensorDataEntity create(TemperatureSensorDataDTO temperatureSensorDataDTO){
        TemperatureSensorDataEntity temperatureSensorDataEntity = new TemperatureSensorDataEntity();
        temperatureSensorDataEntity.setData(temperatureSensorDataDTO.getData());
        temperatureSensorDataEntity.setDate(temperatureSensorDataDTO.getDate());

        return repository.save(temperatureSensorDataEntity);
    }

    public TemperatureSensorDataEntity update(TemperatureSensorDataDTO temperatureSensorDataDTO, Long id){
        return repository.findById(id).map(temperatureSensorDataEntity -> {
            temperatureSensorDataEntity.setTemperatureSensor(temperatureSensorDataDTO.getTemperatureSensor());
            temperatureSensorDataEntity.setData(temperatureSensorDataDTO.getData());
            temperatureSensorDataEntity.setDate(temperatureSensorDataDTO.getDate());

            return repository.save(temperatureSensorDataEntity);
        }).orElseGet(() -> {
            TemperatureSensorDataEntity temperatureSensorDataEntity = new TemperatureSensorDataEntity();
            temperatureSensorDataEntity.setId(id);
            temperatureSensorDataEntity.setTemperatureSensor(temperatureSensorDataDTO.getTemperatureSensor());
            temperatureSensorDataEntity.setData(temperatureSensorDataDTO.getData());
            temperatureSensorDataEntity.setDate(temperatureSensorDataDTO.getDate());

            return repository.save(temperatureSensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
