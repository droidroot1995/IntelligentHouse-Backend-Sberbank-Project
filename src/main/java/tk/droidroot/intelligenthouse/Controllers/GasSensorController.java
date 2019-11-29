package tk.droidroot.intelligenthouse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.droidroot.intelligenthouse.DTO.GasSensorDTO;
import tk.droidroot.intelligenthouse.Models.GasSensorEntity;
import tk.droidroot.intelligenthouse.Service.GasSensorService;

import java.util.List;

@RestController
public class GasSensorController {

    @Autowired
    private GasSensorService service;

    @GetMapping("/gsensor/all/")
    public List<GasSensorDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/gsensor/{id}")
    public GasSensorDTO get(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/gsensor/create/")
    public GasSensorEntity create(@RequestBody GasSensorDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/gsensor/update/{id}")
    public GasSensorEntity update(@RequestBody GasSensorDTO dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    @DeleteMapping("/gsensor/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
