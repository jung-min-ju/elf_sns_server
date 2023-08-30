package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsBootRepository extends JpaRepository<Comments, Long>, CommentsRepository{
    Optional<Comments> findBycommentId(String commentId);
}
