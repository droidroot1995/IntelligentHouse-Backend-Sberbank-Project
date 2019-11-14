package tk.droidroot.intelligenthouse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.droidroot.intelligenthouse.DTO.AlarmDTO;
import tk.droidroot.intelligenthouse.Models.AlarmEntity;
import tk.droidroot.intelligenthouse.Service.AlarmService;

@RestController
public class AlarmController {

    @Autowired
    private AlarmService service;

    @GetMapping("/alarm/{id}")
    public AlarmDTO get(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/alarm/create/")
    public AlarmEntity create(@RequestBody AlarmDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/alarm/update/{id}")
    public AlarmEntity update(@RequestBody AlarmDTO dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    @DeleteMapping("/alarm/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
