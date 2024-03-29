package ToyProject.SNS.Controller;

import ToyProject.SNS.DTO.UploadDTO;
import ToyProject.SNS.Service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class EditContentsController {
    private ContentsService contentsService;
    private CommentsService commentsService;
    private ImageFileService imageFileService;
    private PackingService packingService;
    private UserService userService;


    public EditContentsController(ContentsService contentsService, CommentsService commentsService,
                                  ImageFileService imageFileService, PackingService packingService, UserService userService) {
        this.contentsService = contentsService;
        this.commentsService = commentsService;
        this.imageFileService = imageFileService;
        this.packingService = packingService;
        this.userService = userService;
    }


    @PostMapping("/upload")
    ResponseEntity<Map<String,Object>> uploadToSNS(UploadDTO uploadDTO){
        Map<String, Object> response = null;

        //받아온 데이터의 내용이 너무 길때, 막아주는 함수도 필요함-> 이건 디테일이니까 나중엥~
        if(uploadDTO.getStandard().equals("editComment")){ //댓글 수정 경우
            commentsService.editComment(uploadDTO.getId(), uploadDTO.getContent());
        }
        else if(uploadDTO.getStandard().equals("comment")){
            commentsService.addComment(uploadDTO.getId(), uploadDTO.getContent());
        }
        else if(uploadDTO.getStandard().equals("content")){
            contentsService.uploadContent(uploadDTO.getId(), uploadDTO.getContent());
        }
        else{ //frined 친구 추가일 경우

        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/deleteComment")
    ResponseEntity<Map<String,Object>> delete(@RequestParam("commentId") String commentId){
        Map<String, Object> response = null;
        commentsService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
