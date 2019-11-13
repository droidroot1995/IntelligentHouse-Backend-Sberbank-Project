package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import tk.droidroot.intelligenthouse.DTO.HumiditySensorDataDTO;
import tk.droidroot.intelligenthouse.Models.HumiditySensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.HumiditySensorDataRepository;

import javax.persistence.EntityNotFoundException;

public class HumiditySensorDataService {

    @Autowired
    private HumiditySensorDataRepository repository;

    public HumiditySensorDataDTO findById(Long id) {
        try {
            HumiditySensorDataEntity HumiditySensorData = repository.getOne(id);
            HumiditySensorDataDTO dto = new HumiditySensorDataDTO();
            dto.setId(HumiditySensorData.getId());
            dto.setData(HumiditySensorData.getData());
            dto.setDate(HumiditySensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public HumiditySensorDataEntity create(HumiditySensorDataDTO HumiditySensorDataDTO){
        HumiditySensorDataEntity HumiditySensorDataEntity = new HumiditySensorDataEntity();
        HumiditySensorDataEntity.setData(HumiditySensorDataDTO.getData());
        HumiditySensorDataEntity.setDate(HumiditySensorDataDTO.getDate());

        return repository.save(HumiditySensorDataEntity);
    }

    public HumiditySensorDataEntity update(HumiditySensorDataDTO HumiditySensorDataDTO, Long id){
        return repository.findById(id).map(HumiditySensorDataEntity -> {
            HumiditySensorDataEntity.setData(HumiditySensorDataDTO.getData());
            HumiditySensorDataEntity.setDate(HumiditySensorDataDTO.getDate());

            return repository.save(HumiditySensorDataEntity);
        }).orElseGet(() -> {
            HumiditySensorDataEntity HumiditySensorDataEntity = new HumiditySensorDataEntity();
            HumiditySensorDataEntity.setId(id);
            HumiditySensorDataEntity.setData(HumiditySensorDataDTO.getData());
            HumiditySensorDataEntity.setDate(HumiditySensorDataDTO.getDate());

            return repository.save(HumiditySensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
