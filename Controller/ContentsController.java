package ToyProject.SNS.Controller;

import ToyProject.SNS.DTO.ContentsRequest;
import ToyProject.SNS.Entity.Comments;
import ToyProject.SNS.Entity.Contents;
import ToyProject.SNS.Entity.ContentsUser;
import ToyProject.SNS.Entity.ImageFile;
import ToyProject.SNS.Service.CommentsService;
import ToyProject.SNS.Service.ContentsService;
import ToyProject.SNS.Service.ImageFileService;
import ToyProject.SNS.Service.PackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
public class ContentsController {
    private ContentsService contentsService;
    private CommentsService commentsService;
    private ImageFileService imageFileService;
    private PackingService packingService;

    public ContentsController(ContentsService contentsService,
                              CommentsService commentsService,
                              ImageFileService imageFileService,
                              PackingService packingService )
    {
        this.contentsService = contentsService;
        this.commentsService = commentsService;
        this.imageFileService = imageFileService;
        this.packingService = packingService;
    }


    @PostMapping("/getContents")
    ResponseEntity <Map<String, Object>> getcontents(@RequestBody ContentsRequest contentsRequest) throws Exception {

        Random random = new Random();
        int seed = random.nextInt(1000) + 1;

        Map<String, Object> contentsData = null;
        if (contentsRequest.getContentId()==null) {
            //데이터 생성
            imageFileService.findByImagesFiles(); //이미지 경로 저장
            contentsService.createContents(seed, contentsRequest.getRequiredPage()); //컨텐츠 만들기
            commentsService.createComments(); // 댓글 만들기

            contentsData = packingService.ContentsPage(contentsRequest.getRequiredPage());
        }
        else{

        }
        return ResponseEntity.status(HttpStatus.OK).body(contentsData);
    }
}


// state: "SUCCESS",
//         payload:{
//         totalPage: totalPage,
//         data: [{
//         id: //콘텐츠 아이디 돌려주면 됨
//         author: { //저자
//         id: "userId_12312", //저자 아이디
//         imgUrl: "../img/test_img/사람_1.jpg" //저자 프로필 사진
//         },
//         createAt: 1692706863808, //숫자로
//         imgUrl: ["../img/test_img/사람_5.jpg"// 이미지 url
//         content: "하루 죙일 버튜얼라이즈 인피니티로드를 만졌다., //내용
//         comments: [{
//         commentId:"commentId_123412",
//         userId:"userId_123123",
//         createAt:1698469752808,
//         comment:"와 진짜 개공감 ㅇㅈ"
//         },{
//         commentId:"commentId_123412",
//         userId:"userId_123123",
//         createAt:1698469752808,
//         comment:"와 진짜 개공감 ㅇㅈ"
//         }] //댓글은 두개만 반환
//         },
//         {
//         //위 데이터 형식으로 data 배열을 totalPage 만큼 만든 후, 각 인덱스에 해당 데이터 형식으로 다 넣어주면 됨
//         }]
//         }
