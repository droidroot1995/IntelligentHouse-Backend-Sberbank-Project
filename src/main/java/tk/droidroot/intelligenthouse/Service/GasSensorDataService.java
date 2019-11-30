package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.GasSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.GasSensorDataEntity;
import tk.droidroot.intelligenthouse.Repositories.GasSensorDataRepository;
import tk.droidroot.intelligenthouse.Repositories.GasSensorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service
public class GasSensorDataService {

    @Autowired
    private GasSensorDataRepository repository;

    @Autowired
    private GasSensorRepository sensorRepository;

    public GasSensorDataDTO findById(Long id) {
        try {
            GasSensorDataEntity gasSensorData = repository.getOne(id);
            GasSensorDataDTO dto = new GasSensorDataDTO();
            dto.setId(gasSensorData.getId());
            dto.setGasSensor(gasSensorData.getGasSensor().getId());
            dto.setData(gasSensorData.getData());
            dto.setDate(gasSensorData.getDate());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public GasSensorDataDTO findLastBySensorId(Long id) {
        try {
            List<GasSensorDataEntity> tsde_list = repository.findAllByGasSensorId(id);
            GasSensorDataDTO dto = new GasSensorDataDTO();

            if(tsde_list.size() > 0) {
                GasSensorDataEntity tsde = tsde_list.get(tsde_list.size() - 1);
                dto.setId(tsde.getId());
                dto.setGasSensor(tsde.getGasSensor().getId());
                dto.setData(tsde.getData());
                dto.setDate(tsde.getDate());
            }
            else {
                dto.setId((long)0);
                dto.setGasSensor(id);
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

    public GasSensorDataEntity create(GasSensorDataDTO gasSensorDataDTO){
        GasSensorDataEntity gasSensorDataEntity = new GasSensorDataEntity();
        gasSensorDataEntity.setGasSensor(sensorRepository.getOne(gasSensorDataDTO.getGasSensor()));
        gasSensorDataEntity.setData(gasSensorDataDTO.getData());
        gasSensorDataEntity.setDate(gasSensorDataDTO.getDate());
        return repository.save(gasSensorDataEntity);
    }

    public GasSensorDataEntity update(GasSensorDataDTO gasSensorDataDTO, Long id){
        return repository.findById(id).map(gasSensorDataEntity -> {
            gasSensorDataEntity.setGasSensor(sensorRepository.getOne(gasSensorDataDTO.getGasSensor()));
            gasSensorDataEntity.setData(gasSensorDataDTO.getData());
            gasSensorDataEntity.setDate(gasSensorDataDTO.getDate());

            return repository.save(gasSensorDataEntity);
        }).orElseGet(() -> {
            GasSensorDataEntity gasSensorDataEntity = new GasSensorDataEntity();
            gasSensorDataEntity.setId(id);
            gasSensorDataEntity.setGasSensor(sensorRepository.getOne(gasSensorDataDTO.getGasSensor()));
            gasSensorDataEntity.setData(gasSensorDataDTO.getData());
            gasSensorDataEntity.setDate(gasSensorDataDTO.getDate());

            return repository.save(gasSensorDataEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
