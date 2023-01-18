package wat.edu.pl.pchorevidence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import wat.edu.pl.pchorevidence.dto.RankRequest;
import wat.edu.pl.pchorevidence.dto.RankResponse;
import wat.edu.pl.pchorevidence.entity.Rank;
import wat.edu.pl.pchorevidence.exception.EntityNotFound;
import wat.edu.pl.pchorevidence.service.RankService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/rank")
public class RankController {
    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping()
    public ResponseEntity<List<RankResponse>> getAllRank() {
        List<RankResponse> rankOptional = rankService.getAll();
        return new ResponseEntity<>(rankOptional, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RankResponse> getRankById(@PathVariable String id) {
        Optional<RankResponse> cadetOptional = rankService.getRankById(id);
        if (cadetOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cadetOptional.get(), HttpStatus.OK);
    }

    @GetMapping("{id}/title")
    public ResponseEntity<String> getRankTitleById(@PathVariable String id) {
        Optional<RankResponse> rankOptional = rankService.getRankById(id);
        if (rankOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rankOptional.get().getTitle(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createRank(@RequestBody RankRequest rankRequest) {
        return new ResponseEntity<>(rankService.save(rankRequest).getId(), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RankResponse> updateRank(@PathVariable String id, @RequestParam String title) {
        try {
            return new ResponseEntity<>(rankService.update(id, title), HttpStatus.OK);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RankResponse> deleteRank(@PathVariable String id) {
        try {
            return new ResponseEntity<>(rankService.delete(id), HttpStatus.OK);
        } catch (EntityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
