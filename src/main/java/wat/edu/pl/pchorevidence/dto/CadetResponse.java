package wat.edu.pl.pchorevidence.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import wat.edu.pl.pchorevidence.entity.Rank;
import wat.edu.pl.pchorevidence.repository.RankRepository;

@Data
public class CadetResponse {
    private String id;
    private String rank;
    private String name;
    private String surname;
    private boolean presence;

    public CadetResponse(String id, Rank rank, String name, String surname, boolean presence) {
            this.id = id;
            this.rank = rank.getTitle();
            this.name = name;
            this.surname = surname;
            this.presence = presence;


    }

}
