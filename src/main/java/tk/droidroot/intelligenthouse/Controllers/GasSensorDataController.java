package tk.droidroot.intelligenthouse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.droidroot.intelligenthouse.DTO.GasSensorDataDTO;
import tk.droidroot.intelligenthouse.Models.GasSensorDataEntity;
import tk.droidroot.intelligenthouse.Service.GasSensorDataService;

@RestController
public class GasSensorDataController {

    @Autowired
    private GasSensorDataService service;

    @GetMapping("/gsensordata/{id}")
    public GasSensorDataDTO get(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/gsensordata/last/{sensorId}")
    public GasSensorDataDTO getLast(@PathVariable("sensorId") Long id) {
        return service.findLastBySensorId(id);
    }

    @PostMapping("/gsensordata/create/")
    public GasSensorDataEntity create(@RequestBody GasSensorDataDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/gsensordata/update/{id}")
    public GasSensorDataEntity update(@RequestBody GasSensorDataDTO dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    @DeleteMapping("/gsensordata/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
