package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.HumiditySensorDTO;
import tk.droidroot.intelligenthouse.Models.HumiditySensorEntity;
import tk.droidroot.intelligenthouse.Repositories.HumiditySensorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class HumiditySensorService {

    @Autowired
    private HumiditySensorRepository repository;

    public List<HumiditySensorDTO> getAll() {
        try {
            List<HumiditySensorEntity> hse_lst = repository.findAll();
            List<HumiditySensorDTO> hs_lst = new ArrayList<>();
            for (HumiditySensorEntity hse: hse_lst) {
                HumiditySensorDTO dto = new HumiditySensorDTO();
                dto.setId(hse.getId());
                dto.setName(hse.getName());
                hs_lst.add(dto);
            }

            return hs_lst;
        }
        catch (EntityNotFoundException e) {
            System.out.println("Table is empty");
        }

        return null;
    }

    public HumiditySensorDTO findById(Long id) {
        try {
            HumiditySensorEntity humiditySensor = repository.getOne(id);
            HumiditySensorDTO dto = new HumiditySensorDTO();
            dto.setId(humiditySensor.getId());
            dto.setName(humiditySensor.getName());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public HumiditySensorEntity create(HumiditySensorDTO humiditySensorDTO){
        HumiditySensorEntity humiditySensorEntity = new HumiditySensorEntity();
        humiditySensorEntity.setName(humiditySensorDTO.getName());

        return repository.save(humiditySensorEntity);
    }

    public HumiditySensorEntity update(HumiditySensorDTO humiditySensorDTO, Long id){
        return repository.findById(id).map(humiditySensorEntity -> {
            humiditySensorEntity.setName(humiditySensorDTO.getName());

            return repository.save(humiditySensorEntity);
        }).orElseGet(() -> {
            HumiditySensorEntity humiditySensorEntity = new HumiditySensorEntity();
            humiditySensorEntity.setId(id);
            humiditySensorEntity.setName(humiditySensorDTO.getName());

            return repository.save(humiditySensorEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
