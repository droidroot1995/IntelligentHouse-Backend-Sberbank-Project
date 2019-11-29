package tk.droidroot.intelligenthouse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.droidroot.intelligenthouse.DTO.HumiditySensorDTO;
import tk.droidroot.intelligenthouse.Models.HumiditySensorEntity;
import tk.droidroot.intelligenthouse.Service.HumiditySensorService;

import java.util.List;


@RestController
public class HumiditySensorController {

    @Autowired
    private HumiditySensorService service;

    @GetMapping("/hsensor/all/")
    public List<HumiditySensorDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/hsensor/{id}")
    public HumiditySensorDTO get(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/hsensor/create/")
    public HumiditySensorEntity create(@RequestBody HumiditySensorDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/hsensor/update/{id}")
    public HumiditySensorEntity update(@RequestBody HumiditySensorDTO dto, @PathVariable("id") Long id){
        return service.update(dto, id);
    }

    @DeleteMapping("/hsensor/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
