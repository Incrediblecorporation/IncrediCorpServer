package incredicorpserver.repository;

import incredicorpserver.beans.W_Level;
import incredicorpserver.beans.W_Mode;
import incredicorpserver.beans.W_Score;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface W_ScoreRepository extends CrudRepository<W_Score,Long> {
    @Override
    List<W_Score> findAll();

    List<W_Score> findByLevelAndModeAndScoreL(W_Level level, W_Mode mode, String scoreL);
}
