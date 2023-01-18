package wat.edu.pl.pchorevidence.entity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Data
public class Cadet {
    @MongoId
    private String id;
    private String rankID;
    private String name;
    private String surname;

    private String email;
    private boolean presence;


    public Cadet(String name, String surname, String rankID) {
        this.name = name;
        this.surname = surname;
        this.rankID = rankID;
        this.presence = true;
    }
}
