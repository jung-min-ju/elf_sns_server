package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberBootRepository extends JpaRepository<Member, Long>, MemberRepository  {
    @Override
    Optional<Member> findByEmail(String email);
}
