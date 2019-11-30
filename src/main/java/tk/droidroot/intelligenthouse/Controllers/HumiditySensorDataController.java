package tk.droidroot.intelligenthouse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.droidroot.intelligenthouse.DTO.HumiditySensorDataDTO;
import tk.droidroot.intelligenthouse.Models.HumiditySensorDataEntity;
import tk.droidroot.intelligenthouse.Service.HumiditySensorDataService;


@RestController
public class HumiditySensorDataController {

    @Autowired
    private HumiditySensorDataService service;

    @GetMapping("/hsensordata/{id}")
    public HumiditySensorDataDTO get(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/hsensordata/last/{sensorId}")
    public HumiditySensorDataDTO getLast(@PathVariable("sensorId") Long id) {
        return service.findLastBySensorId(id);
    }

    @PostMapping("/hsensordata/create/")
    public HumiditySensorDataEntity create(@RequestBody HumiditySensorDataDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/hsensordata/update/{id}")
    public HumiditySensorDataEntity update(@RequestBody HumiditySensorDataDTO dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    @DeleteMapping("/hsensordata/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
