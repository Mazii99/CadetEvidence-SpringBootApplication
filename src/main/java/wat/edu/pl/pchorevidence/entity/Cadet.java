package wat.edu.pl.pchorevidence.entity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Data
public class Cadet {
    @MongoId
    private String id;
    private String rank;
    private String name;
    private String surname;
    private boolean presence;

    public Cadet(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.rank = "szer. ";
        this.presence = true;
    }
    //    public Cadet(){
//        this.rank = "szer. ";
//        this.presence = true;
//    }
}
