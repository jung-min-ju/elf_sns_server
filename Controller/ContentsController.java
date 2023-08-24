package ToyProject.SNS.Controller;

import ToyProject.SNS.DTO.ContentsRequest;
import ToyProject.SNS.Entity.Contents;
import ToyProject.SNS.Entity.ContentsUser;
import ToyProject.SNS.Service.ContentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Controller
public class ContentsController {
    private ContentsService contentsService;

    public ContentsController(ContentsService contentsService) {
        this.contentsService = contentsService;
    }

    @PostMapping("/getContents")
    ResponseEntity <Map<String, Object>> getcontents(@RequestBody ContentsRequest contentsRequest){

        Random random = new Random();
        int seed = random.nextInt(100)+1;

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();

        if(contentsRequest.getContentId().equals(null)){
           List <Contents> contents = contentsService.createContents(seed, contentsRequest.getRequiredPage());
        }
        return (ResponseEntity<Map<String, Object>>) response;
    }
}
