package tk.droidroot.intelligenthouse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.droidroot.intelligenthouse.DTO.TemperatureSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.TemperatureSensorDataEntity;
import tk.droidroot.intelligenthouse.Service.TemperatureSensorDataService;

@RestController
public class TemperatureSensorDataController {

    @Autowired
    private TemperatureSensorDataService service;

    @GetMapping("/tsensordata/{id}")
    public TemperatureSensorDataDTO get(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/tsensordata/last/{sensorId}")
    public TemperatureSensorDataDTO getLast(@PathVariable("sensorId") Long id) {
        return service.findLastBySensorId(id);
    }

    @PostMapping("/tsensordata/create/")
    public TemperatureSensorDataEntity create(@RequestBody TemperatureSensorDataDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/tsensordata/update/{id}")
    public TemperatureSensorDataEntity update(@RequestBody TemperatureSensorDataDTO dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    @DeleteMapping("/tsensordata/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
