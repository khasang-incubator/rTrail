package io.khasang.rtrail.controller;

import io.khasang.rtrail.dto.CatDTO;
import io.khasang.rtrail.entity.Cat;
import io.khasang.rtrail.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatService catService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CatDTO addCat(@RequestBody Cat cat) {
        return catService.addCat(cat);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CatDTO getCatById(@PathVariable(value = "id") String id) {
        return catService.getCatById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<CatDTO> getCats() {
        return catService.getAllCats();
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<CatDTO> getCatsByName(@PathVariable(value = "name") String name) {
        return catService.getCatsByName(name);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CatDTO deleteCat(@RequestParam(value = "id") String id) {
        return catService.deleteCat(Long.parseLong(id));
    }
}
