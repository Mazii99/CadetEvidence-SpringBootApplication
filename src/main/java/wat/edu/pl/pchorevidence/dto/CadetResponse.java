package wat.edu.pl.pchorevidence.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CadetResponse {
    private String id;
    private String rank;
    private String name;
    private String surname;
    private boolean presence;

    public CadetResponse(String id,String rank, String name, String surname) {

            this.id = id;
            this.rank = rank;
            this.name = name+" ";
            this.surname = surname;
            this.presence = true;


    }
    public CadetResponse(String id, String name, String surname) {

            this.id = id;
            this.rank = "szer. ";
            this.name = name+" ";
            this.surname = surname;
            this.presence = true;


    }
}
