package ToyProject.SNS.Controller;

import ToyProject.SNS.DTO.ContentsRequest;
import ToyProject.SNS.Service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ContentsController {
    private ContentsService contentsService;
    private CommentsService commentsService;
    private ImageFileService imageFileService;
    private PackingService packingService;
    private UserService userService;

    public ContentsController(ContentsService contentsService,
                              CommentsService commentsService,
                              ImageFileService imageFileService,
                              PackingService packingService,
                              UserService userService)
    {
        this.contentsService = contentsService;
        this.commentsService = commentsService;
        this.imageFileService = imageFileService;
        this.packingService = packingService;
        this.userService = userService;
    }


    @PostMapping("/getContents")
    ResponseEntity <Map<String, Object>> getcontents(@RequestBody ContentsRequest contentsRequest) throws Exception {

        Random random = new Random();
        int seed = random.nextInt(1000) + 1;

        Map<String, Object> contentsData = null;
        if (contentsRequest.getContentId()==null) {
            //데이터 생성
            imageFileService.findByImagesFiles(); //이미지 경로 저장
            contentsService.createContents(seed, contentsRequest.getRequiredPage()); //컨텐츠 만들기 - crateAT순으로 정렬해줌
            commentsService.createComments(); // 댓글 만들기
            userService.createUser(); //유저 생성
            userService.createFriendship(); //친구 관계 설정

            contentsData = packingService.ContentsPage(String.valueOf(0L),contentsRequest.getRequiredPage(),"");
        }
        else{
            if(contentsRequest.getRequiredPage()>0){
                //contents id를 기준으로, 더 작은 id만큼의 requiredpage가 필요함
                contentsData = packingService.ContentsPage(contentsRequest.getContentId(),contentsRequest.getRequiredPage(),"plus");
            }
            else{
                 //contents id를 기준으로, 더 큰 id만큼의 requiredpage가 필요함
                contentsData = packingService.ContentsPage(contentsRequest.getContentId(),contentsRequest.getRequiredPage(),"minus");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(contentsData);
    }

    @PostMapping("/getFriendship") //친구 검색했을때, 해당하는 모든 사람의 인원 보내주기
    ResponseEntity <Map<String, Object>> getFriendship(@RequestParam("userId") String userId) {
        Map<String, Object> response = null;
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }


}

