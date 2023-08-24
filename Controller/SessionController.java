package ToyProject.SNS.Controller;

import ToyProject.SNS.DTO.SessionDTO;
import ToyProject.SNS.Repository.MemberRepository;
import ToyProject.SNS.Service.MemberService;
import com.mysql.cj.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class SessionController {

    private MemberService memberService;
    private MemberRepository memberRepository;

    public SessionController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/sessionCheck")
    ResponseEntity<Map<String, Object>> sessionCheck(@RequestBody SessionDTO sessionDTO){
        try {
            System.out.println("sessionDTO = " + sessionDTO.getSessionId());
            String user_id = memberService.getUserIdBySessionId(sessionDTO.getSessionId());
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> payload = new HashMap<>();

            if(user_id!=null){
                payload.put("userId", user_id);
                response.put("state", "SUCCESS");
                response.put("payload",payload);

                System.out.println("response = " + response);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else{
                payload.put("userId", null);
                response.put("state", "FAILURE");
                response.put("payload",payload);

                System.out.println("response = " + response);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (IllegalStateException e) {
            e.getStackTrace();
            log.error("SessionController Error message : ",e);

            Map<String, Object> response = new HashMap<>();
            Map<String, Object> payload = new HashMap<>();

            payload.put("userId", null);
            response.put("state", "ERROR");
            response.put("payload",payload);

            System.out.println("response = " + response);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

