package wat.edu.pl.pchorevidence.service;

import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.edu.pl.pchorevidence.repository.CadetRepository;
import wat.edu.pl.pchorevidence.repository.RankRepository;

@Service
@Slf4j
public class ScriptService {
    private final CadetRepository cadetRepository;
    private final RankRepository rankRepository;

    @Autowired
    public ScriptService(CadetRepository cadetRepository, RankRepository rankRepository) {
        this.cadetRepository = cadetRepository;
        this.rankRepository = rankRepository;
    }

    public String exec(String script) {
        try (Context context = Context.newBuilder("js")
                .allowAllAccess(true)
                .build()) {
            var bindings = context.getBindings("js");
            bindings.putMember("articleRepository", cadetRepository);
            bindings.putMember("authorRepository", rankRepository);
            return context.eval("js", script).toString();
        } catch (PolyglotException e) {
            log.error("Error executing", e);
            return e.getMessage() + "\n" + e.getSourceLocation().toString();
        }
    }
}