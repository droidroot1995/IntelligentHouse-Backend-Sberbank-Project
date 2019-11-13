package tk.droidroot.intelligenthouse.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.AlarmDTO;
import tk.droidroot.intelligenthouse.Models.AlarmEntity;
import tk.droidroot.intelligenthouse.Repositories.AlarmRepository;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class AlarmService {

    @Autowired
    private AlarmRepository repository;

    public AlarmDTO findById(Long id) {
        try {
            AlarmEntity Alarm = repository.getOne(id);
            AlarmDTO dto = new AlarmDTO();
            dto.setId(Alarm.getId());
            dto.setName(Alarm.getName());
            dto.setTime(Alarm.getTime());
            dto.setState(Alarm.getState());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public AlarmEntity create(AlarmDTO AlarmDTO){
        AlarmEntity AlarmEntity = new AlarmEntity();
        AlarmEntity.setName(AlarmDTO.getName());
        AlarmEntity.setTime(AlarmDTO.getTime());
        AlarmEntity.setState(AlarmDTO.getState());

        return repository.save(AlarmEntity);
    }

    public AlarmEntity update(AlarmDTO AlarmDTO, Long id){
        return repository.findById(id).map(AlarmEntity -> {
            AlarmEntity.setName(AlarmDTO.getName());
            AlarmEntity.setTime(AlarmDTO.getTime());
            AlarmEntity.setState(AlarmDTO.getState());

            return repository.save(AlarmEntity);
        }).orElseGet(() -> {
            AlarmEntity AlarmEntity = new AlarmEntity();
            AlarmEntity.setId(id);
            AlarmEntity.setName(AlarmDTO.getName());
            AlarmEntity.setTime(AlarmDTO.getTime());
            AlarmEntity.setState(AlarmDTO.getState());

            return repository.save(AlarmEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
