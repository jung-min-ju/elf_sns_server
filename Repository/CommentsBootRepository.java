package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsBootRepository extends JpaRepository<Comments, Long>, CommentsRepository{
}
