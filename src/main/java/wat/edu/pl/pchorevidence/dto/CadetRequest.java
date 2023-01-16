package wat.edu.pl.pchorevidence.dto;
import lombok.Data;
@Data
public class CadetRequest {
    private String rankID;
    private String name;
    private String surname;
    private String email;

    private boolean presence;
}
