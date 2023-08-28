package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.ContentsUser;
import ToyProject.SNS.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsUserBootRepository extends JpaRepository<ContentsUser, Long>, ContentsUserRepository{

}
