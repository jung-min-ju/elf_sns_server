package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Comments;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository {
    Comments save(Comments comments);
    @Query(value = "SELECT * FROM comments ORDER BY RAND() LIMIT 2", nativeQuery = true)
    List<Comments> findRandomTwo();
}
