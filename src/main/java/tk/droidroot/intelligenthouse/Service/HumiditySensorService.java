package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.HumiditySensorDTO;
import tk.droidroot.intelligenthouse.Models.HumiditySensorEntity;
import tk.droidroot.intelligenthouse.Repositories.HumiditySensorRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class HumiditySensorService {

    @Autowired
    private HumiditySensorRepository repository;

    public HumiditySensorDTO findById(Long id) {
        try {
            HumiditySensorEntity HumiditySensor = repository.getOne(id);
            HumiditySensorDTO dto = new HumiditySensorDTO();
            dto.setId(HumiditySensor.getId());
            dto.setName(HumiditySensor.getName());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public HumiditySensorEntity create(HumiditySensorDTO HumiditySensorDTO){
        HumiditySensorEntity HumiditySensorEntity = new HumiditySensorEntity();
        HumiditySensorEntity.setName(HumiditySensorDTO.getName());

        return repository.save(HumiditySensorEntity);
    }

    public HumiditySensorEntity update(HumiditySensorDTO HumiditySensorDTO, Long id){
        return repository.findById(id).map(HumiditySensorEntity -> {
            HumiditySensorEntity.setName(HumiditySensorDTO.getName());

            return repository.save(HumiditySensorEntity);
        }).orElseGet(() -> {
            HumiditySensorEntity HumiditySensorEntity = new HumiditySensorEntity();
            HumiditySensorEntity.setId(id);
            HumiditySensorEntity.setName(HumiditySensorDTO.getName());

            return repository.save(HumiditySensorEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
