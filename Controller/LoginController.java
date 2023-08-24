package ToyProject.SNS.Controller;
import ToyProject.SNS.DTO.LoginDTO;
import ToyProject.SNS.Domain.MemberSessionInfo;
import ToyProject.SNS.Entity.Member;
import ToyProject.SNS.Service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController {

  private MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signIn")
    ResponseEntity<Map<String, Object>> LoginPost(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        try {
            Optional<Member> memberOptional = memberService.login(loginDTO.getEmail(), loginDTO.getPassword());

            Map<String, Object> response = new HashMap<>();
            Map<String, Object> payload = new HashMap<>();
            HttpSession session = request.getSession();
            String user_id = memberService.findIdByEmail(loginDTO.getEmail());

            if (memberOptional.isPresent()) { //이건 service에 들어가야 할 구조. 즉 잘못 짬! 아;;

                Member loggedInMember = memberOptional.get(); 
                
                MemberSessionInfo memberSessionInfo = new MemberSessionInfo(); 
                memberSessionInfo.setId(loggedInMember.getId().toString());
                memberSessionInfo.setEmail(loggedInMember.getEmail());
                memberSessionInfo.setPassword(loggedInMember.getPassword());

                //Session session = sessionRepository.createSession();
                session.setAttribute("LoggedMember", memberSessionInfo); //세션생성
                String session_id = session.getId(); //세션id 가져오기
                System.out.println("session_id = " + session_id); //잘 나오는 중

                payload.put("sessionId", session_id);
                payload.put("userId", user_id);

                response.put("state", "SUCCESS");
                response.put("payload", payload);

                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                payload.put("sessionId", "NULL");
                payload.put("userId", user_id);

                response.put("state", "FAILURE");
                response.put("payload", payload);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (IllegalStateException e) {
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> payload = new HashMap<>();
            String user_id = memberService.findIdByEmail(loginDTO.getEmail());

            payload.put("sessionId", "NULL");
            payload.put("userId", user_id);

            response.put("state", "ERROR");
            response.put("payload", payload);
            e.getStackTrace();
            return ResponseEntity.badRequest().body(response);
        }
    }
}

