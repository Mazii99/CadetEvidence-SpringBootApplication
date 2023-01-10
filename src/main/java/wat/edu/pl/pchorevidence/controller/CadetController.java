package wat.edu.pl.pchorevidence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import wat.edu.pl.pchorevidence.dto.CadetRequest;
import wat.edu.pl.pchorevidence.dto.CadetResponse;
import wat.edu.pl.pchorevidence.entity.Cadet;
import wat.edu.pl.pchorevidence.exception.EntityNotFound;
import wat.edu.pl.pchorevidence.service.CadetService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/cadet")
public class CadetController {
    private final CadetService cadetService;

    @Autowired
    public CadetController(CadetService cadetService) {
        this.cadetService = cadetService;
    }

    @GetMapping()
    public ResponseEntity<List<CadetResponse>> getAllCadet() {
        List<CadetResponse> cadetOptional = cadetService.getAll();
        return new ResponseEntity<>(cadetOptional, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CadetResponse> getCadetByIdVar(@PathVariable String id) {
        try {
            CadetResponse cadetOptional = cadetService.getCadetById(id);
            return new ResponseEntity<>(cadetOptional, HttpStatus.OK);
        }
        catch(EntityNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        /*if (cadetOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/

    }

    @GetMapping("{id}/surname")
    public ResponseEntity<String> getCadetSurnameById(@PathVariable String id) {
        try {
            CadetResponse cadetOptional = cadetService.getCadetById(id);
            return new ResponseEntity<>(cadetOptional.getSurname(), HttpStatus.OK);
        }
        catch(EntityNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<String> createCadet(@RequestBody CadetRequest cadetRequest) {
        return new ResponseEntity<>(cadetService.save(cadetRequest).getId(), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CadetResponse> updateCadet(@PathVariable String id) {
        try {
            return new ResponseEntity<>(cadetService.update(id), HttpStatus.OK);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{id}/presence")
    public ResponseEntity<CadetResponse> switchCadetPresence(@PathVariable String id) {
        try {
            return new ResponseEntity<>(cadetService.switchPresence(id), HttpStatus.OK);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<CadetResponse> deleteCadet(@PathVariable String id) {
        try {
            return new ResponseEntity<>(cadetService.delete(id), HttpStatus.OK);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
