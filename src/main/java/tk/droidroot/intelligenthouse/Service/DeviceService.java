package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.DeviceDTO;
import tk.droidroot.intelligenthouse.Models.DeviceEntity;
import tk.droidroot.intelligenthouse.Repositories.DeviceRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class DeviceService {

    @Autowired
    private DeviceRepository repository;

    public DeviceDTO findById(Long id) {
        try {
            DeviceEntity device = repository.getOne(id);
            DeviceDTO dto = new DeviceDTO();
            dto.setId(device.getId());
            dto.setName(device.getName());
            dto.setState(device.getState());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public DeviceEntity create(DeviceDTO deviceDTO){
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setName(deviceDTO.getName());
        deviceEntity.setState(deviceDTO.getState());

        return repository.save(deviceEntity);
    }

    public DeviceEntity update(DeviceDTO deviceDTO, Long id){
        return repository.findById(id).map(deviceEntity -> {
            deviceEntity.setName(deviceDTO.getName());
            deviceEntity.setState(deviceDTO.getState());

            return repository.save(deviceEntity);
        }).orElseGet(() -> {
            DeviceEntity deviceEntity = new DeviceEntity();
            deviceEntity.setId(id);
            deviceEntity.setName(deviceDTO.getName());
            deviceEntity.setState(deviceDTO.getState());

            return repository.save(deviceEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
