package wat.edu.pl.pchorevidence.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.edu.pl.pchorevidence.dto.CadetRequest;
import wat.edu.pl.pchorevidence.dto.CadetResponse;
import wat.edu.pl.pchorevidence.entity.Cadet;
import wat.edu.pl.pchorevidence.exception.EntityNotFound;
import wat.edu.pl.pchorevidence.repository.CadetRepository;
import wat.edu.pl.pchorevidence.repository.RankRepository;

import java.util.List;

@Service
public class CadetService {
    @Autowired
    private CadetRepository cadetRepository;
    private RankRepository rankRepository;

    public CadetService(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }





    public CadetResponse getCadetById(String id) throws EntityNotFound {
        Cadet cadet = cadetRepository.findById(id).orElseThrow(EntityNotFound::new);
        return new CadetResponse(cadet.getId(), rankRepository.findById(cadet.getRankID()).orElseThrow(), cadet.getName(), cadet.getSurname(), cadet.getEmail() , cadet.isPresence());
    }

    public CadetResponse save(CadetRequest CadetRequest) {
        Cadet cadet = new Cadet(CadetRequest.getName(), CadetRequest.getSurname(), CadetRequest.getRankID());
        System.out.println(cadet);
        cadet = cadetRepository.save(
                cadet
        );
        System.out.println(cadet);
        return new CadetResponse(cadet.getId(),  rankRepository.findById(cadet.getRankID()).orElseThrow(), cadet.getName(), cadet.getSurname(), cadet.getEmail() ,cadet.isPresence());
    }

    public List<CadetResponse> getAll() {
        return cadetRepository.findAll()
                .stream()
                .map(cadet -> new CadetResponse(cadet.getId(),  rankRepository.findById(cadet.getRankID()).orElseThrow(), cadet.getName(), cadet.getSurname(), cadet.getEmail(), cadet.isPresence()))
                .toList();
    }
    public CadetResponse switchPresence(String id) throws EntityNotFound{
        Cadet cadet = cadetRepository.findById(id).orElseThrow(EntityNotFound::new);
        cadet.setPresence(!cadet.isPresence());
        cadetRepository.save(cadet);
        return new CadetResponse(cadet.getId(), rankRepository.findById(cadet.getRankID()).orElseThrow(EntityNotFound::new), cadet.getName(), cadet.getSurname(), cadet.getEmail(), cadet.isPresence());
    }
    public CadetResponse update(String id) throws EntityNotFound {
        Cadet cadet = cadetRepository.findById(id).orElseThrow(EntityNotFound::new);
        if (cadet.getRankID().equals("63bdb2d18a4e590d340ae26b")) {
            cadet.setRankID("63bdb2d68a4e590d340ae26c");
        } else if (cadet.getRankID().equals("63bdb2d68a4e590d340ae26c")) {
            cadet.setRankID("63bdb2de8a4e590d340ae26d");

        } else if (cadet.getRankID().equals("63bdb2de8a4e590d340ae26d")) {
            cadet.setRankID("63bdb2e28a4e590d340ae26e");

        } else if (cadet.getRankID().equals("63bdb2e28a4e590d340ae26e")) {
            cadet.setRankID("63bdb2e68a4e590d340ae26f");

        } else if (cadet.getRankID().equals("63bdb2e68a4e590d340ae26f")) {
            cadet.setRankID("63bdb2ea8a4e590d340ae270");
        }
        cadet = cadetRepository.save(cadet);
        return new CadetResponse(cadet.getId(), rankRepository.findById(cadet.getRankID()).orElseThrow(EntityNotFound::new), cadet.getName(), cadet.getSurname(), cadet.getEmail(), cadet.isPresence());
    }

    public CadetResponse delete(String id) throws EntityNotFound {
        Cadet cadet = cadetRepository.findById(id).orElseThrow(EntityNotFound::new);

        cadetRepository.deleteById(id);
        System.out.println("Usunięto z bazy: " + cadet.getName() + " " + cadet.getSurname());
        return new CadetResponse(cadet.getId(),  rankRepository.findById(cadet.getRankID()).orElseThrow(), cadet.getName(), cadet.getSurname(), cadet.getEmail(), cadet.isPresence());

    }
}
