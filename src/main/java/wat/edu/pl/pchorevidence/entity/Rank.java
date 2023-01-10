package wat.edu.pl.pchorevidence.entity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Data
public class Rank {
    @MongoId
    private String id;
    private String title;

    public Rank(String title) {
        this.title = title;
    }
}
