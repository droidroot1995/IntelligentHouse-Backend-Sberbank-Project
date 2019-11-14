package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.GasSensorDTO;
import tk.droidroot.intelligenthouse.Models.GasSensorEntity;
import tk.droidroot.intelligenthouse.Repositories.GasSensorRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class GasSensorService {

    @Autowired
    private GasSensorRepository repository;

    public GasSensorDTO findById(Long id) {
        try {
            GasSensorEntity GasSensor = repository.getOne(id);
            GasSensorDTO dto = new GasSensorDTO();
            dto.setId(GasSensor.getId());
            dto.setName(GasSensor.getName());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public GasSensorEntity create(GasSensorDTO GasSensorDTO){
        GasSensorEntity GasSensorEntity = new GasSensorEntity();
        GasSensorEntity.setName(GasSensorDTO.getName());

        return repository.save(GasSensorEntity);
    }

    public GasSensorEntity update(GasSensorDTO GasSensorDTO, Long id){
        return repository.findById(id).map(GasSensorEntity -> {
            GasSensorEntity.setName(GasSensorDTO.getName());

            return repository.save(GasSensorEntity);
        }).orElseGet(() -> {
            GasSensorEntity GasSensorEntity = new GasSensorEntity();
            GasSensorEntity.setId(id);
            GasSensorEntity.setName(GasSensorDTO.getName());

            return repository.save(GasSensorEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
    
}
