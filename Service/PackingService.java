package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.Comments;
import ToyProject.SNS.Entity.Contents;
import ToyProject.SNS.Entity.ImageFile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PackingService {

    private ContentsService contentsService;
    private CommentsService commentsService;
    private ImageFileService imageFileService;

    public PackingService(ContentsService contentsService, CommentsService commentsService, ImageFileService imageFileService) {
        this.contentsService = contentsService;
        this.commentsService = commentsService;
        this.imageFileService = imageFileService;
    }

    public Map<String, Object> ContentsPage (Long totalPage) {
        Map<String, Object> response = new HashMap<>();
        response.put("state", "SUCCESS");

        Map<String, Object> payload = new HashMap<>();
        payload.put("totlaPage", totalPage);

        List<Map<String, Object>> contentList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            Map<String, Object> dataMap = new HashMap<>();

            //작성자 정보 저장하기 - 배열이 아니네,..?
            //List<Map<String, Object>> authorList = new ArrayList<>();
            List<Contents> author = contentsService.returnOneContent();
            Map<String, Object> authorMap = new HashMap<>();
            for(Contents content : author){
                authorMap.put("id", content.getAuthorId()); //author
                authorMap.put("imgUrl", content.getImgUrl()); //author
                //authorList.add(authorMap);
            }

            //List<ImageFile> ImageList = new ArrayList<>();
            List<ImageFile> imageFile = imageFileService.returnImages(); //이미지 리스트 여러개 받기
            List<String> imageUrl = new ArrayList<>();
            for(ImageFile imageFiles : imageFile){
                imageUrl.add(imageFiles.getFilePath()); //author
            }

            //글의 정보 저장하기
            List<Contents> contents = contentsService.returnOneContent();
            for(Contents content : contents){
                dataMap.put("id", content.getContentsId());
                dataMap.put("createAt", content.getCteateAt());
                dataMap.put("content", content.getContent());
            }

            //댓글 정보 저장하기
            List<Map<String, Object>> commentsList = new ArrayList<>();
            List<Comments> randomComments = commentsService.returnTwoComments();
            for (Comments randomComment : randomComments) {
                Map<String, Object> commentMap = new HashMap<>();
                commentMap.put("commentId", randomComment.getCommentId());
                commentMap.put("userId", randomComment.getUserId());
                commentMap.put("createAt", randomComment.getCreateAt());
                commentMap.put("comment", randomComment.getComment());
                commentsList.add(commentMap);
            }
            dataMap.put("comments", commentsList); // 랜덤 댓글 리스트 추가
            dataMap.put("author", authorMap);
            dataMap.put("imgUrl",imageUrl);
            contentList.add(dataMap);
        }

        payload.put("data", contentList);
        response.put("payload", payload);

        return response;
    }

}
