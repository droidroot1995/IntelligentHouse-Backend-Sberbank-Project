package tk.droidroot.intelligenthouse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.droidroot.intelligenthouse.DTO.TemperatureSensorDTO;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorEntity;
import tk.droidroot.intelligenthouse.Service.TemperatureSensorService;

import java.util.List;

@RestController
public class TemperatureSensorController {

    @Autowired
    private TemperatureSensorService service;

    @GetMapping("/tsensor/all/")
    public List<TemperatureSensorDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/tsensor/{id}")
    public TemperatureSensorDTO get(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/tsensor/create/")
    public TemperatureSensorEntity create(@RequestBody TemperatureSensorDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/tsensor/update/{id}")
    public TemperatureSensorEntity update(@RequestBody TemperatureSensorDTO dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    @DeleteMapping("/tsensor/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
