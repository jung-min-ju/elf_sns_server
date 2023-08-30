package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendBootRepository extends JpaRepository<Friend, Long>, FriendRepository{
}
