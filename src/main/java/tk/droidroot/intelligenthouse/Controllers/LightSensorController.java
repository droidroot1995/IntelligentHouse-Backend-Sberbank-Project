package tk.droidroot.intelligenthouse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.droidroot.intelligenthouse.DTO.LightSensorDTO;
import tk.droidroot.intelligenthouse.Models.LightSensorEntity;
import tk.droidroot.intelligenthouse.Service.LightSensorService;

import java.util.List;


@RestController
public class LightSensorController {

    @Autowired
    private LightSensorService service;

    @GetMapping("/lsensor/all/")
    public List<LightSensorDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/lsensor/{id}")
    public LightSensorDTO get(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/lsensor/create/")
    public LightSensorEntity create(@RequestBody LightSensorDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/lsensor/update/{id}")
    public LightSensorEntity update(@RequestBody LightSensorDTO dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    @DeleteMapping("/lsensor/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
