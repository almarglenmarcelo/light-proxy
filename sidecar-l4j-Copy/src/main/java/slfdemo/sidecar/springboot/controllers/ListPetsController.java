package slfdemo.sidecar.springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import slfdemo.sidecar.springboot.Utility;

@RestController
public class ListPetsController {
    @GetMapping("/spring/pets")
    public String listPets() {

        String s = "{\"id\":1,\"name\":\"catten\",\"tag\":\"cat\"},{\"id\":2,\"name\":\"doggy\",\"tag\":\"dog\"}";

        return Utility.writePrettyJson(s);
    }
}