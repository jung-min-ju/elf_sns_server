package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.ContentsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsUserBootRepository extends JpaRepository<ContentsUser, Long>, ContentsUserRepository{

}
