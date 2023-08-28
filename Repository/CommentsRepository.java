package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Comments;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository {
    Comments save(Comments comments);
    Optional<Comments> findById(long id);
    List<Comments> findAll();
}
