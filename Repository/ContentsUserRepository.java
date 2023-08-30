package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Comments;
import ToyProject.SNS.Entity.ContentsUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ContentsUserRepository {
    ContentsUser save(ContentsUser contentsUser);
    Optional<ContentsUser> findById(long id);
}
