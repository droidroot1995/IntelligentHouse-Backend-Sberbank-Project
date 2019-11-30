package tk.droidroot.intelligenthouse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.droidroot.intelligenthouse.DTO.LightSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.LightSensorDataEntity;
import tk.droidroot.intelligenthouse.Service.LightSensorDataService;

@RestController
public class LightSensorDataController {

    @Autowired
    private LightSensorDataService service;

    @GetMapping("/lsensordata/{id}")
    public LightSensorDataDTO get(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/lsensordata/last/{sensorId}")
    public LightSensorDataDTO getLast(@PathVariable("sensorId") Long id) {
        return service.findLastBySensorId(id);
    }

    @PostMapping("/lsensordata/create/")
    public LightSensorDataEntity create(@RequestBody LightSensorDataDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/lsensordata/update/{id}")
    public LightSensorDataEntity update(@RequestBody LightSensorDataDTO dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    @DeleteMapping("/lsensordata/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
