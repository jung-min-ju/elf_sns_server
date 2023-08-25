package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsBootRepository extends JpaRepository<Contents, Long>, ContentsRepository{
}
