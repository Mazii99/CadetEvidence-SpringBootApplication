package wat.edu.pl.pchorevidence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wat.edu.pl.pchorevidence.reflection.FieldInformation;
import wat.edu.pl.pchorevidence.reflection.Reflection;

@SpringBootApplication
public class PchorEvidenceApplication {

    public static void main(String[] args) {
        FieldInformation fieldInformation = new FieldInformation();
        Reflection.apply(fieldInformation.getFieldName(),fieldInformation.getFieldType());
        SpringApplication.run(PchorEvidenceApplication.class, args);
    }

}
