package wat.edu.pl.pchorevidence.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import wat.edu.pl.pchorevidence.entity.Rank;
import wat.edu.pl.pchorevidence.repository.RankRepository;

@Data
public class CadetResponse {
    private String id;
    private Rank rank;
    private String name;
    private String surname;
    private String email;
    private boolean presence;

    public CadetResponse(String id, Rank rank, String name, String surname, String email, boolean presence) {
            this.id = id;
            //this.rank = rank.getTitle();
            this.rank = rank;
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.presence = presence;


    }

}
