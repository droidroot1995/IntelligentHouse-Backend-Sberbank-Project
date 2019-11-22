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
            AlarmEntity alarm = repository.getOne(id);
            AlarmDTO dto = new AlarmDTO();
            dto.setId(alarm.getId());
            dto.setName(alarm.getName());
            dto.setTime(alarm.getTime());
            dto.setState(alarm.getState());

            return dto;
        }
        catch(EntityNotFoundException e){
            System.out.println("Entity not found");
        }

        return null;
    }

    public AlarmEntity create(AlarmDTO alarmDTO){
        AlarmEntity alarmEntity = new AlarmEntity();
        alarmEntity.setName(alarmDTO.getName());
        alarmEntity.setTime(alarmDTO.getTime());
        alarmEntity.setState(alarmDTO.getState());

        return repository.save(alarmEntity);
    }

    public AlarmEntity update(AlarmDTO alarmDTO, Long id){
        return repository.findById(id).map(alarmEntity -> {
            alarmEntity.setName(alarmDTO.getName());
            alarmEntity.setTime(alarmDTO.getTime());
            alarmEntity.setState(alarmDTO.getState());

            return repository.save(alarmEntity);
        }).orElseGet(() -> {
            AlarmEntity alarmEntity = new AlarmEntity();
            alarmEntity.setId(id);
            alarmEntity.setName(alarmDTO.getName());
            alarmEntity.setTime(alarmDTO.getTime());
            alarmEntity.setState(alarmDTO.getState());

            return repository.save(alarmEntity);
        });
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
