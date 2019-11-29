package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.GasSensorDTO;
import tk.droidroot.intelligenthouse.Models.GasSensorEntity;
import tk.droidroot.intelligenthouse.Repositories.GasSensorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class GasSensorService {

    @Autowired
    private GasSensorRepository repository;

    public List<GasSensorDTO> getAll() {
        try {
            List<GasSensorEntity> gse_lst = repository.findAll();
            List<GasSensorDTO> gs_lst = new ArrayList<>();
            for (GasSensorEntity gse: gse_lst) {
                GasSensorDTO dto = new GasSensorDTO();
                dto.setId(gse.getId());
                dto.setName(gse.getName());
                gs_lst.add(dto);
            }

            return gs_lst;
        }
        catch (EntityNotFoundException e) {
            System.out.println("Table is empty");
        }

        return null;
    }

    public GasSensorDTO findById(Long id) {
        try {
            GasSensorEntity gasSensor = repository.getOne(id);
            GasSensorDTO dto = new GasSensorDTO();
            dto.setId(gasSensor.getId());
            dto.setName(gasSensor.getName());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public GasSensorEntity create(GasSensorDTO gasSensorDTO){
        GasSensorEntity gasSensorEntity = new GasSensorEntity();
        gasSensorEntity.setName(gasSensorDTO.getName());

        return repository.save(gasSensorEntity);
    }

    public GasSensorEntity update(GasSensorDTO gasSensorDTO, Long id){
        return repository.findById(id).map(gasSensorEntity -> {
            gasSensorEntity.setName(gasSensorDTO.getName());

            return repository.save(gasSensorEntity);
        }).orElseGet(() -> {
            GasSensorEntity gasSensorEntity = new GasSensorEntity();
            gasSensorEntity.setId(id);
            gasSensorEntity.setName(gasSensorDTO.getName());

            return repository.save(gasSensorEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
    
}
