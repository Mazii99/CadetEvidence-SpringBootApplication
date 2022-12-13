package wat.edu.pl.pchorevidence.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.edu.pl.pchorevidence.dto.CadetRequest;
import wat.edu.pl.pchorevidence.dto.CadetResponse;
import wat.edu.pl.pchorevidence.entity.Cadet;
import wat.edu.pl.pchorevidence.exception.EntityNotFound;
import wat.edu.pl.pchorevidence.repository.CadetRepository;

import java.util.List;
import java.util.Optional;
@Service
public class CadetService {
    @Autowired
    private CadetRepository cadetRepository;



    /*public CadetService(CadetRepository CadetRepository) {
        this.cadetRepository = CadetRepository;
    }*/

    public Optional<CadetResponse> getCadetById(String id) {
        return cadetRepository.findById(id)
                .map(cadet -> new CadetResponse(cadet.getId(), cadet.getName(), cadet.getSurname()));
    }

    public CadetResponse save(CadetRequest CadetRequest) {
        Cadet Cadet = new Cadet(CadetRequest.getName(), CadetRequest.getSurname());
        System.out.println(Cadet);
        Cadet = cadetRepository.save(
                Cadet
        );
        System.out.println(Cadet);
        return new CadetResponse(Cadet.getId(), Cadet.getName(), Cadet.getSurname());
    }

    public List<CadetResponse> getAll() {
        return cadetRepository.findAll()
                .stream()
                .map(Cadet -> new CadetResponse(Cadet.getId(), Cadet.getName(), Cadet.getSurname()))
                .toList();
    }

    public CadetResponse update(String id) throws EntityNotFound {
        Cadet Cadet = cadetRepository.findById(id).orElseThrow(EntityNotFound::new);
        if(Cadet.getRank().equals("szer. ")){
            Cadet.setRank("st. szer. ") ;

        }else if(Cadet.getRank().equals("st. szer. ")){
            Cadet.setRank("kpr. ");

        }else if(Cadet.getRank().equals("kpr. ")){
            Cadet.setRank("st. kpr. ");

        }else if(Cadet.getRank().equals("st. kpr. ")){
            Cadet.setRank("plut. ");

        }else if(Cadet.getRank().equals("plut. ")){
            Cadet.setRank("sierż. ");
        }
        Cadet = cadetRepository.save(Cadet);
        return new CadetResponse(Cadet.getId(),Cadet.getRank(), Cadet.getName(), Cadet.getSurname());
    }
    public CadetResponse delete(String id) throws EntityNotFound{
        Cadet Cadet = cadetRepository.findById(id).orElseThrow(EntityNotFound::new);
        cadetRepository.deleteById(id);
        System.out.println("Usunięto z bazy: "+ Cadet.getName()+ " "+Cadet.getSurname());
        return new CadetResponse(Cadet.getId(), Cadet.getName(), Cadet.getSurname());

    }
}
