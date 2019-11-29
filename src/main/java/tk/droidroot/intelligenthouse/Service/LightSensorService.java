package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.LightSensorDTO;
import tk.droidroot.intelligenthouse.Models.LightSensorEntity;
import tk.droidroot.intelligenthouse.Repositories.LightSensorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class LightSensorService {

    @Autowired
    private LightSensorRepository repository;
    
    public List<LightSensorDTO> getAll() {
        try {
            List<LightSensorEntity> lse_lst = repository.findAll();
            List<LightSensorDTO> ls_lst = new ArrayList<>();
            for (LightSensorEntity tse: lse_lst) {
                LightSensorDTO dto = new LightSensorDTO();
                dto.setId(tse.getId());
                dto.setName(tse.getName());
                ls_lst.add(dto);
            }

            return ls_lst;
        }
        catch (EntityNotFoundException e) {
            System.out.println("Table is empty");
        }

        return null;
    }
    

    public LightSensorDTO findById(Long id) {
        try {
            LightSensorEntity lightSensor = repository.getOne(id);
            LightSensorDTO dto = new LightSensorDTO();
            dto.setId(lightSensor.getId());
            dto.setName(lightSensor.getName());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public LightSensorEntity create(LightSensorDTO lightSensorDTO){
        LightSensorEntity lightSensorEntity = new LightSensorEntity();
        lightSensorEntity.setName(lightSensorDTO.getName());

        return repository.save(lightSensorEntity);
    }

    public LightSensorEntity update(LightSensorDTO lightSensorDTO, Long id){
        return repository.findById(id).map(lightSensorEntity -> {
            lightSensorEntity.setName(lightSensorDTO.getName());

            return repository.save(lightSensorEntity);
        }).orElseGet(() -> {
            LightSensorEntity lightSensorEntity = new LightSensorEntity();
            lightSensorEntity.setId(id);
            lightSensorEntity.setName(lightSensorDTO.getName());

            return repository.save(lightSensorEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
