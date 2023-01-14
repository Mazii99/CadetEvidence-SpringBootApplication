package wat.edu.pl.pchorevidence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wat.edu.pl.pchorevidence.service.ScriptService;

@RestController
@CrossOrigin
@RequestMapping("/api/script")
public class ScriptController {
    private final ScriptService scriptService;

    @Autowired
    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PutMapping()
    public ResponseEntity<String> execScript() {
        //@RequestBody String script powinno byc w nawiasie
       String script = """
                        var Cadet = Java.type('wat.edu.pl.pchorevidence.entity.Cadet');
                        var Rank = Java.type('wat.edu.pl.pchorevidence.entity.Rank');
                        
                        var Set = Java.type('java.util.Set');

                        var abackiCadet = new Cadet("Adam","Abacki","63bdb2e68a4e590d340ae26f");
                        cadetRepository.save(abackiCadet).getId();
                                """;

        return new ResponseEntity<>(scriptService.exec(script), HttpStatus.OK) ;
    }
}
