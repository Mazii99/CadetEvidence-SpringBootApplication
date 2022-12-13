package wat.edu.pl.pchorevidence.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wat.edu.pl.pchorevidence.entity.Cadet;
@Repository
    public interface CadetRepository
        extends MongoRepository<Cadet, String>{

}

