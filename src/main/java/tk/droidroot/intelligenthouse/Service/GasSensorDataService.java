package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import tk.droidroot.intelligenthouse.DTO.GasSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.GasSensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.GasSensorDataRepository;

import javax.persistence.EntityNotFoundException;

public class GasSensorDataService {

    @Autowired
    private GasSensorDataRepository repository;

    public GasSensorDataDTO findById(Long id) {
        try {
            GasSensorDataEntity GasSensorData = repository.getOne(id);
            GasSensorDataDTO dto = new GasSensorDataDTO();
            dto.setId(GasSensorData.getId());
            dto.setData(GasSensorData.getData());
            dto.setDate(GasSensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public GasSensorDataEntity create(GasSensorDataDTO GasSensorDataDTO){
        GasSensorDataEntity GasSensorDataEntity = new GasSensorDataEntity();
        GasSensorDataEntity.setData(GasSensorDataDTO.getData());
        GasSensorDataEntity.setDate(GasSensorDataDTO.getDate());

        return repository.save(GasSensorDataEntity);
    }

    public GasSensorDataEntity update(GasSensorDataDTO GasSensorDataDTO, Long id){
        return repository.findById(id).map(GasSensorDataEntity -> {
            GasSensorDataEntity.setData(GasSensorDataDTO.getData());
            GasSensorDataEntity.setDate(GasSensorDataDTO.getDate());

            return repository.save(GasSensorDataEntity);
        }).orElseGet(() -> {
            GasSensorDataEntity GasSensorDataEntity = new GasSensorDataEntity();
            GasSensorDataEntity.setId(id);
            GasSensorDataEntity.setData(GasSensorDataDTO.getData());
            GasSensorDataEntity.setDate(GasSensorDataDTO.getDate());

            return repository.save(GasSensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
