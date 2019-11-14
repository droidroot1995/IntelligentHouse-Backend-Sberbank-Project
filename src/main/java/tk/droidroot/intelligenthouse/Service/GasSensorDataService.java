package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.GasSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.GasSensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.GasSensorDataRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class GasSensorDataService {

    @Autowired
    private GasSensorDataRepository repository;

    public GasSensorDataDTO findById(Long id) {
        try {
            GasSensorDataEntity gasSensorData = repository.getOne(id);
            GasSensorDataDTO dto = new GasSensorDataDTO();
            dto.setId(gasSensorData.getId());
            dto.setGasSensor(gasSensorData.getGasSensor());
            dto.setData(gasSensorData.getData());
            dto.setDate(gasSensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public GasSensorDataEntity create(GasSensorDataDTO gasSensorDataDTO){
        GasSensorDataEntity gasSensorDataEntity = new GasSensorDataEntity();
        gasSensorDataEntity.setGasSensor(gasSensorDataDTO.getGasSensor());
        gasSensorDataEntity.setData(gasSensorDataDTO.getData());
        gasSensorDataEntity.setDate(gasSensorDataDTO.getDate());

        return repository.save(gasSensorDataEntity);
    }

    public GasSensorDataEntity update(GasSensorDataDTO gasSensorDataDTO, Long id){
        return repository.findById(id).map(gasSensorDataEntity -> {
            gasSensorDataEntity.setGasSensor(gasSensorDataDTO.getGasSensor());
            gasSensorDataEntity.setData(gasSensorDataDTO.getData());
            gasSensorDataEntity.setDate(gasSensorDataDTO.getDate());

            return repository.save(gasSensorDataEntity);
        }).orElseGet(() -> {
            GasSensorDataEntity gasSensorDataEntity = new GasSensorDataEntity();
            gasSensorDataEntity.setId(id);
            gasSensorDataEntity.setGasSensor(gasSensorDataDTO.getGasSensor());
            gasSensorDataEntity.setData(gasSensorDataDTO.getData());
            gasSensorDataEntity.setDate(gasSensorDataDTO.getDate());

            return repository.save(gasSensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
