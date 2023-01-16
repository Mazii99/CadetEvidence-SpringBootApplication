package wat.edu.pl.pchorevidence.mapper;
import org.springframework.stereotype.Service;
import wat.edu.pl.pchorevidence.dto.RankRequest;
import wat.edu.pl.pchorevidence.dto.RankResponse;
import wat.edu.pl.pchorevidence.entity.Rank;

@Service
public class RankMapper {

    public Rank map(RankRequest rankRequest) {
        Rank rank = new Rank();
        rank.setTitle(rankRequest.getTitle());
        fillRankRequest(rank, rankRequest);
        return rank;
    }

    private void fillRankRequest(Rank rank, RankRequest rankRequest) {
//        author.setRank(authorRequest.getRank());
        // empty for byte buddy
    }

    public RankResponse map(Rank rank) {
        RankResponse rankResponse = new RankResponse(rank.getId(), rank.getTitle());
        fillRank(rankResponse, rank);
        return rankResponse;
    }

    private void fillRank(RankResponse rankResponse, Rank rank) {
        //authorResponse.setRank(author.getRank());
        // empty for byte buddy
    }


}

