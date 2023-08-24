package ToyProject.SNS.Repository;


import ToyProject.SNS.Entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //null 값 존재할 수 있으니 Optional 사용
    Optional<Member> findByEmail(String email);
    List<Member> findAll(); //하나의 객체를 찾아야 하므로 List 사용
}
