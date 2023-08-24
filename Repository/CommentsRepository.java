package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Comments;

import java.util.List;

public interface CommentsRepository {
    Comments save(Comments comments);
    List<Comments> findById(long id);
}
