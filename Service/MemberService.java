package ToyProject.SNS.Service;


import ToyProject.SNS.Domain.MemberSessionInfo;
import ToyProject.SNS.Entity.Member;
import ToyProject.SNS.Repository.MemberRepository;
import org.springframework.session.Session;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // Spring Session에서 제공하는 인터페이스 중 하나로, 세션을 검색하기 위한 인덱스 기반의 메서드를 포함
    private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    public MemberService(MemberRepository memberRepository, FindByIndexNameSessionRepository<? extends Session> sessionRepository) {
        this.memberRepository = memberRepository;
        this.sessionRepository = sessionRepository;
    }


    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증 -> 핵심 비즈니스 로직임
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //해당 멤버가 존재하는지 확인해주는 메서드
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> { //'result에 null 이 아닌 이미 값이 있다면'인 의미의 코드
                    //result라는 객체가 Optional이라는 것에 감싸져 있어서 그런거임(Optional의 메서드라 생각)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<Member> login(String email, String password) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent() && member.get().getPassword().equals(password)) {
            return member; // 로그인 성공 시 해당 멤버 반환
        }
        else {
            return Optional.empty(); // 로그인 실패 시 빈 Optional 반환
        }
    }

    public String findIdByEmail(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        String member_id = String.valueOf(member.get().getId());
        return member_id;
    }


    public String getUserIdBySessionId(String sessionId) {

        System.out.println("Session ID: " + sessionId);

        Session session = sessionRepository.findById(sessionId);
        System.out.println("Session: " + session);

        if (session != null) {
            MemberSessionInfo storedMemberInfo = (MemberSessionInfo) session.getAttribute("LoggedMember");
            String userId = storedMemberInfo.getId();
            System.out.println("User ID from Session: " + userId);

            if (userId != null) {
                return userId;
            }
        }
        return null;
    }
}
