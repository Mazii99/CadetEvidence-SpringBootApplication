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
        //@RequestBody String script
       String script = """
                        var Cadet = Java.type('wat.edu.pl.pchorevidence.entity.Cadet');
                        var Rank = Java.type('wat.edu.pl.pchorevidence.entity.Rank');
                        var Set = Java.type('java.util.Set');
                        function email(){
                        
                        for(cadet of cadetRepository.findAll()){              
                        var cadetName = cadet.getName();
                        var cadetSurname = cadet.getSurname();
                        var email = cadetName+'.'+cadetSurname+'@student.wat.edu.pl';
                        cadet.setEmail(email);
                        cadetRepository.save(cadet);
                        }
                        return cadetRepository.findAll();
                        }
                        email();
                        
                                """;
        return new ResponseEntity<>(scriptService.exec(script), HttpStatus.OK) ;
    }
}
