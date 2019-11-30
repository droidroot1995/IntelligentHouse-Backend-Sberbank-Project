package tk.droidroot.intelligenthouse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.droidroot.intelligenthouse.DTO.DeviceDTO;
import tk.droidroot.intelligenthouse.Models.DeviceEntity;
import tk.droidroot.intelligenthouse.Service.DeviceService;

import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceService service;

    @GetMapping("/device/all/")
    public List<DeviceDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/device/{id}")
    public DeviceDTO get(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/device/create/")
    @CrossOrigin
    public DeviceEntity create(@RequestBody DeviceDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/device/update/{id}")
    @CrossOrigin
    public DeviceEntity update(@RequestBody DeviceDTO dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    @DeleteMapping("/device/delete/{id}")
    @CrossOrigin
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
