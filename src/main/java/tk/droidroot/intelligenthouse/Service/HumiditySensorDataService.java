package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.HumiditySensorDataDTO;
import tk.droidroot.intelligenthouse.Models.HumiditySensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.HumiditySensorDataRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class HumiditySensorDataService {

    @Autowired
    private HumiditySensorDataRepository repository;

    public HumiditySensorDataDTO findById(Long id) {
        try {
            HumiditySensorDataEntity humiditySensorData = repository.getOne(id);
            HumiditySensorDataDTO dto = new HumiditySensorDataDTO();
            dto.setId(humiditySensorData.getId());
            dto.setHumiditySensor(humiditySensorData.getHumiditySensor());
            dto.setData(humiditySensorData.getData());
            dto.setDate(humiditySensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public HumiditySensorDataEntity create(HumiditySensorDataDTO humiditySensorDataDTO){
        HumiditySensorDataEntity humiditySensorDataEntity = new HumiditySensorDataEntity();
        humiditySensorDataEntity.setHumiditySensor(humiditySensorDataDTO.getHumiditySensor());
        humiditySensorDataEntity.setData(humiditySensorDataDTO.getData());
        humiditySensorDataEntity.setDate(humiditySensorDataDTO.getDate());

        return repository.save(humiditySensorDataEntity);
    }

    public HumiditySensorDataEntity update(HumiditySensorDataDTO humiditySensorDataDTO, Long id){
        return repository.findById(id).map(humiditySensorDataEntity -> {
            humiditySensorDataEntity.setHumiditySensor(humiditySensorDataDTO.getHumiditySensor());
            humiditySensorDataEntity.setData(humiditySensorDataDTO.getData());
            humiditySensorDataEntity.setDate(humiditySensorDataDTO.getDate());

            return repository.save(humiditySensorDataEntity);
        }).orElseGet(() -> {
            HumiditySensorDataEntity humiditySensorDataEntity = new HumiditySensorDataEntity();
            humiditySensorDataEntity.setId(id);
            humiditySensorDataEntity.setData(humiditySensorDataDTO.getData());
            humiditySensorDataEntity.setDate(humiditySensorDataDTO.getDate());

            return repository.save(humiditySensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
