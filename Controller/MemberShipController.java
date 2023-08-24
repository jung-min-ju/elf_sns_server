package ToyProject.SNS.Controller;

import ToyProject.SNS.DTO.MemberShipDTO;
import ToyProject.SNS.Domain.MemberSessionInfo;
import ToyProject.SNS.Entity.Member;
import ToyProject.SNS.Repository.MemberRepository;
import ToyProject.SNS.Service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberShipController {
    private MemberService memberService;
    private MemberRepository memberRepository;

    public MemberShipController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/signUp")
    ResponseEntity<Map<String, Object>> MemberShip(@RequestBody MemberShipDTO memberShipDTO) {

        try {
            Member member = new Member();
            member.setEmail(memberShipDTO.getEmail());
            member.setName(memberShipDTO.getName());
            member.setPassword(memberShipDTO.getPassword());
            member.setPhoneNumber(memberShipDTO.getPassword());

            Long memberId = memberService.join(member);

            Map<String, Object> response = new HashMap<>();
            response.put("state", "SUCCESS");
            response.put("payload", "회원가입에 성공하였습니다.");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalStateException e) {
            if(e.getMessage().equals("이미 존재하는 회원입니다.")) {

                Map<String, Object> failResponse = new HashMap<>();
                failResponse.put("state", "FAILURE");
                failResponse.put("payload", "해당 회원은 이미 존재합니다.");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failResponse);
            }
              else{
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("state", "ERROR");
                errorResponse.put("payload", "예외 처리로 인한 오류가 발생했습니다.");

                return ResponseEntity.badRequest().body(errorResponse);
            }
        }
    }
}
