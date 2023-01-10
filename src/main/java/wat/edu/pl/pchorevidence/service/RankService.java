package wat.edu.pl.pchorevidence.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.edu.pl.pchorevidence.dto.RankRequest;
import wat.edu.pl.pchorevidence.dto.RankResponse;
import wat.edu.pl.pchorevidence.entity.Rank;
import wat.edu.pl.pchorevidence.exception.EntityNotFound;
import wat.edu.pl.pchorevidence.repository.RankRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RankService {
    @Autowired
    private RankRepository rankRepository;



    /*public CadetService(CadetRepository CadetRepository) {
        this.cadetRepository = CadetRepository;
    }*/

    public Optional<RankResponse> getRankById(String id) {
        return rankRepository.findById(id)
                .map(cadet -> new RankResponse(cadet.getId(), cadet.getTitle()));
    }

    public RankResponse save(RankRequest RankRequest) {
        Rank Rank = new Rank(RankRequest.getTitle());
        System.out.println(Rank);
        Rank = rankRepository.save(
                Rank
        );
        System.out.println(Rank);
        return new RankResponse(Rank.getId(), Rank.getTitle());
    }

    public List<RankResponse> getAll() {
        return rankRepository.findAll()
                .stream()
                .map(Rank -> new RankResponse(Rank.getId(), Rank.getTitle()))
                .toList();
    }

    public RankResponse update(String id, String newTitle) throws EntityNotFound {
        Rank Rank = rankRepository.findById(id).orElseThrow(EntityNotFound::new);
        Rank.setTitle(newTitle);
        Rank = rankRepository.save(Rank);
        return new RankResponse(Rank.getId(), Rank.getTitle());
    }

    public RankResponse delete(String id) throws EntityNotFound {
        Rank Rank = rankRepository.findById(id).orElseThrow(EntityNotFound::new);
        rankRepository.deleteById(id);
        System.out.println("Usunięto z bazy: " + Rank.getTitle());
        return new RankResponse(Rank.getId(), Rank.getTitle());

    }
}
