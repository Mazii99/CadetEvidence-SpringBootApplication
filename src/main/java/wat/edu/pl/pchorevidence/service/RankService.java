package wat.edu.pl.pchorevidence.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.edu.pl.pchorevidence.dto.RankRequest;
import wat.edu.pl.pchorevidence.dto.RankResponse;
import wat.edu.pl.pchorevidence.entity.Rank;
import wat.edu.pl.pchorevidence.exception.EntityNotFound;
import wat.edu.pl.pchorevidence.repository.RankRepository;
import wat.edu.pl.pchorevidence.mapper.RankMapper;

import java.util.List;
import java.util.Optional;

@Service
public class RankService {
    @Autowired
    private RankRepository rankRepository;
    private RankMapper rankMapper;

    @Autowired
    public RankService(RankRepository rankRepository, RankMapper rankMapper) {
        this.rankRepository = rankRepository;
        this.rankMapper = rankMapper;
    }

    public Optional<RankResponse> getRankById(String id) {
        return rankRepository.findById(id)
                .map(rankMapper::map);
    }

    public RankResponse save(RankRequest rankRequest) {
        Rank rank = rankMapper.map(rankRequest);
        System.out.println(rank);
        rank = rankRepository.save(
                rank
        );
        System.out.println(rank);
        return rankMapper.map(rank);
    }

    public List<RankResponse> getAll() {
        return rankRepository.findAll()
                .stream()
                .map(Rank -> new RankResponse(Rank.getId(), Rank.getTitle()))
                .toList();
    }

    public RankResponse update(String id, String newTitle) throws EntityNotFound {
        Rank rank = rankRepository.findById(id).orElseThrow(EntityNotFound::new);
        rank.setTitle(newTitle);
        rank = rankRepository.save(rank);
        return rankMapper.map(rank);
    }

    public RankResponse delete(String id) throws EntityNotFound {
        Rank Rank = rankRepository.findById(id).orElseThrow(EntityNotFound::new);
        rankRepository.deleteById(id);
        System.out.println("Usunięto z bazy: " + Rank.getTitle());
        return new RankResponse(Rank.getId(), Rank.getTitle());

    }
}
