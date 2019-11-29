package tk.droidroot.intelligenthouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.droidroot.intelligenthouse.DTO.AlarmDTO;
import tk.droidroot.intelligenthouse.Models.AlarmEntity;
import tk.droidroot.intelligenthouse.Repositories.AlarmRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AlarmService {

    @Autowired
    private AlarmRepository repository;

    public List<AlarmDTO> getAll() {
        try {
            List<AlarmEntity> ae_lst = repository.findAll();
            List<AlarmDTO> a_lst = new ArrayList<>();
            for (AlarmEntity ae: ae_lst) {
                AlarmDTO dto = new AlarmDTO();
                dto.setId(ae.getId());
                dto.setName(ae.getName());
                dto.setTime(ae.getTime());
                dto.setState(ae.getState());
                a_lst.add(dto);
            }

            return a_lst;
        }
        catch (EntityNotFoundException e) {
            System.out.println("Table is empty");
        }

        return null;
    }

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
