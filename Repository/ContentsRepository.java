package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Contents;

import java.util.List;

public interface ContentsRepository {
    Contents save(Contents contents);
    List<CommentsRepository> findById(long id);
}
