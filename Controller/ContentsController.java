package ToyProject.SNS.Controller;

import ToyProject.SNS.DTO.ContentsRequest;
import ToyProject.SNS.Entity.Comments;
import ToyProject.SNS.Entity.Contents;
import ToyProject.SNS.Entity.ContentsUser;
import ToyProject.SNS.Service.CommentsService;
import ToyProject.SNS.Service.ContentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Controller
public class ContentsController {
    private ContentsService contentsService;
    private CommentsService commentsService;

    public ContentsController(ContentsService contentsService, CommentsService commentsService) {
        this.contentsService = contentsService;
        this.commentsService = commentsService;
    }


    @PostMapping("/getContents")
    ResponseEntity <Map<String, Object>> getcontents(@RequestBody ContentsRequest contentsRequest){

        Random random = new Random();
        int seed = random.nextInt(1000)+1;

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();

        if(contentsRequest.getContentId().equals("null")){
            List <Contents> contents = contentsService.createContents(seed, contentsRequest.getRequiredPage()); //컨텐츠 만들기
            List <Comments> comments = commentsService.createComments(); // 댓글 만들기

        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
