package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.TemperatureSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorDataEntity;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorEntity;
import tk.droidroot.intelligenthouse.Repositories.TemperatureSensorDataRepository;
import tk.droidroot.intelligenthouse.Repositories.TemperatureSensorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service
public class TemperatureSensorDataService {

    @Autowired
    private TemperatureSensorDataRepository repository;

    @Autowired
    private TemperatureSensorRepository sensorRepository;

    public TemperatureSensorDataDTO findById(Long id) {
        try {
            TemperatureSensorDataEntity temperatureSensorData = repository.getOne(id);
            TemperatureSensorDataDTO dto = new TemperatureSensorDataDTO();
            dto.setId(temperatureSensorData.getId());
            dto.setTemperatureSensor(temperatureSensorData.getTemperatureSensor().getId());
            dto.setData(temperatureSensorData.getData());
            dto.setDate(temperatureSensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public TemperatureSensorDataDTO findLastBySensorId(Long id) {
        try {
            List<TemperatureSensorDataEntity> tsde_list = repository.findAllByTemperatureSensorId(id);
            TemperatureSensorDataDTO dto = new TemperatureSensorDataDTO();

            if(tsde_list.size() > 0) {
                TemperatureSensorDataEntity tsde = tsde_list.get(tsde_list.size() - 1);
                dto.setId(tsde.getId());
                dto.setTemperatureSensor(tsde.getTemperatureSensor().getId());
                dto.setData(tsde.getData());
                dto.setDate(tsde.getDate());
            }
            else {
                dto.setId((long)0);
                dto.setTemperatureSensor(id);
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

    public TemperatureSensorDataEntity create(TemperatureSensorDataDTO temperatureSensorDataDTO){
        TemperatureSensorDataEntity temperatureSensorDataEntity = new TemperatureSensorDataEntity();
        temperatureSensorDataEntity.setData(temperatureSensorDataDTO.getData());
        temperatureSensorDataEntity.setDate(temperatureSensorDataDTO.getDate());
        temperatureSensorDataEntity.setTemperatureSensor(sensorRepository.getOne(temperatureSensorDataDTO.getTemperatureSensor()));

        return repository.save(temperatureSensorDataEntity);
    }

    public TemperatureSensorDataEntity update(TemperatureSensorDataDTO temperatureSensorDataDTO, Long id){
        return repository.findById(id).map(temperatureSensorDataEntity -> {
            temperatureSensorDataEntity.setTemperatureSensor(sensorRepository.getOne(temperatureSensorDataDTO.getTemperatureSensor()));
            temperatureSensorDataEntity.setData(temperatureSensorDataDTO.getData());
            temperatureSensorDataEntity.setDate(temperatureSensorDataDTO.getDate());

            return repository.save(temperatureSensorDataEntity);
        }).orElseGet(() -> {
            TemperatureSensorDataEntity temperatureSensorDataEntity = new TemperatureSensorDataEntity();
            temperatureSensorDataEntity.setId(id);
            temperatureSensorDataEntity.setTemperatureSensor(sensorRepository.getOne(temperatureSensorDataDTO.getTemperatureSensor()));
            temperatureSensorDataEntity.setData(temperatureSensorDataDTO.getData());
            temperatureSensorDataEntity.setDate(temperatureSensorDataDTO.getDate());

            return repository.save(temperatureSensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
