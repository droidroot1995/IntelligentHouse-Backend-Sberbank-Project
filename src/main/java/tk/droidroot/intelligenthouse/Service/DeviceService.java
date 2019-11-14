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
            DeviceEntity Device = repository.getOne(id);
            DeviceDTO dto = new DeviceDTO();
            dto.setId(Device.getId());
            dto.setName(Device.getName());
            dto.setState(Device.getState());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public DeviceEntity create(DeviceDTO DeviceDTO){
        DeviceEntity DeviceEntity = new DeviceEntity();
        DeviceEntity.setName(DeviceDTO.getName());
        DeviceEntity.setState(DeviceDTO.getState());

        return repository.save(DeviceEntity);
    }

    public DeviceEntity update(DeviceDTO DeviceDTO, Long id){
        return repository.findById(id).map(DeviceEntity -> {
            DeviceEntity.setName(DeviceDTO.getName());
            DeviceEntity.setState(DeviceDTO.getState());

            return repository.save(DeviceEntity);
        }).orElseGet(() -> {
            DeviceEntity DeviceEntity = new DeviceEntity();
            DeviceEntity.setId(id);
            DeviceEntity.setName(DeviceDTO.getName());
            DeviceEntity.setState(DeviceDTO.getState());

            return repository.save(DeviceEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
