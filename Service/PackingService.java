package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.Comments;
import ToyProject.SNS.Entity.Contents;
import ToyProject.SNS.Entity.ImageFile;
import ToyProject.SNS.Repository.CommentsRepository;
import ToyProject.SNS.Repository.ContentsBootRepository;
import ToyProject.SNS.Repository.ContentsRepository;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.*;

@Service
public class PackingService {
    private ImageFileService imageFileService;
    private CommentsRepository commentsRepository;
    private ContentsRepository contentsRepository;
    private ContentsBootRepository contentsBootRepository;

    public PackingService(ImageFileService imageFileService, CommentsRepository commentsRepository,
                          ContentsBootRepository contentBootRepository, ContentsRepository contentsRepository) {
        this.imageFileService = imageFileService;
        this.commentsRepository = commentsRepository;
        this.contentsBootRepository = contentBootRepository;
        this.contentsRepository = contentsRepository;
    }

    public Map<String, Object> ContentsPage (String Standard_ContentId, Long requiredPage, String Null) {
        Map<String, Object> response = new HashMap<>();
        response.put("state", "SUCCESS");

        Map<String, Object> payload = new HashMap<>();
        payload.put("totlaPage", requiredPage);

        requiredPage = Math.abs(requiredPage);

        List<Map<String, Object>> contentList = new ArrayList<>();
        for (int i = 0; i < requiredPage; i++) {
            Map<String, Object> dataMap = new HashMap<>();

            //글의 작성자 정보 저장하기 - 최신순으로!
            long contentId = 0;
            if ("plus".equals(Null)) {
                contentId = Long.parseLong(Standard_ContentId) - (requiredPage - i);
            }
            else if ("minus".equals(Null)) {
                contentId = Long.parseLong(Standard_ContentId) + (requiredPage - i);
            }
            else {
                contentId = i + 1;
            }
            Optional<Contents> authorOptional = contentsBootRepository.findByCreateAtID(contentId);

            Map<String, Object> authorMap = new HashMap<>();
            Contents content = null; // Initialize content outside the if statement
            if (authorOptional.isPresent()) {
                content = authorOptional.get();
                authorMap.put("id", content.getAuthorId()); // author
                authorMap.put("imgUrl", content.getImgUrl()); // author
                authorMap.put("contentId",content.getCreateAtID());
            }

            // 글의 정보 저장하기
            dataMap.put("id", content.getContentsId());
            dataMap.put("createAt", content.getCreateAt());
            dataMap.put("content", content.getContent());


            //이미지 정보 저장하기
            List<ImageFile> imageFile = imageFileService.returnImages(); //이미지 리스트 여러개 받기
            List<String> imageUrl = new ArrayList<>();
            for(ImageFile imageFiles : imageFile){
                imageUrl.add(imageFiles.getFilePath()); //author
            }

            //댓글 정보 저장하기
            List<Map<String, Object>> commentsList = new ArrayList<>();
            List<Comments> randomComments = commentsRepository.findRandomTwo();
            for (Comments randomComment : randomComments) {
                Map<String, Object> commentMap = new HashMap<>();
                commentMap.put("commentId", randomComment.getCommentId());
                commentMap.put("userId", randomComment.getUserId());
                commentMap.put("createAt", randomComment.getCreateAt());
                commentMap.put("comment", randomComment.getComment());
                commentsList.add(commentMap);
            }
            dataMap.put("comments", commentsList); // 랜덤 댓글 리스트 추가
            dataMap.put("imgUrl",imageUrl);
            dataMap.put("author", authorMap);
            contentList.add(dataMap);
        }

        payload.put("data", contentList);
        response.put("payload", payload);

        return response;
    }

}
