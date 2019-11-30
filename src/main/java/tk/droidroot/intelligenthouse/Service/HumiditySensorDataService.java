package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.HumiditySensorDataDTO;
import tk.droidroot.intelligenthouse.Models.HumiditySensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.HumiditySensorDataRepository;
import tk.droidroot.intelligenthouse.Repositories.HumiditySensorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service
public class HumiditySensorDataService {

    @Autowired
    private HumiditySensorDataRepository repository;

    @Autowired
    private HumiditySensorRepository sensorRepository;

    public HumiditySensorDataDTO findById(Long id) {
        try {
            HumiditySensorDataEntity humiditySensorData = repository.getOne(id);
            HumiditySensorDataDTO dto = new HumiditySensorDataDTO();
            dto.setId(humiditySensorData.getId());
            dto.setHumiditySensor(humiditySensorData.getHumiditySensor().getId());
            dto.setData(humiditySensorData.getData());
            dto.setDate(humiditySensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public HumiditySensorDataDTO findLastBySensorId(Long id) {
        try {
            List<HumiditySensorDataEntity> tsde_list = repository.findAllByHumiditySensorId(id);
            HumiditySensorDataDTO dto = new HumiditySensorDataDTO();

            if(tsde_list.size() > 0) {
                HumiditySensorDataEntity tsde = tsde_list.get(tsde_list.size() - 1);
                dto.setId(tsde.getId());
                dto.setHumiditySensor(tsde.getHumiditySensor().getId());
                dto.setData(tsde.getData());
                dto.setDate(tsde.getDate());
            }
            else {
                dto.setId((long)0);
                dto.setHumiditySensor(id);
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

    public HumiditySensorDataEntity create(HumiditySensorDataDTO humiditySensorDataDTO){
        HumiditySensorDataEntity humiditySensorDataEntity = new HumiditySensorDataEntity();
        humiditySensorDataEntity.setHumiditySensor(sensorRepository.getOne(humiditySensorDataDTO.getHumiditySensor()));
        humiditySensorDataEntity.setData(humiditySensorDataDTO.getData());
        humiditySensorDataEntity.setDate(humiditySensorDataDTO.getDate());

        return repository.save(humiditySensorDataEntity);
    }

    public HumiditySensorDataEntity update(HumiditySensorDataDTO humiditySensorDataDTO, Long id){
        return repository.findById(id).map(humiditySensorDataEntity -> {
            humiditySensorDataEntity.setHumiditySensor(sensorRepository.getOne(humiditySensorDataDTO.getHumiditySensor()));
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
