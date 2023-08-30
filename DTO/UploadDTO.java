package ToyProject.SNS.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadDTO {
    private String standard; //여기 댓글 업로드라면 "comment", 댓글 수정이라면 "editComment" 게시물 업로드라면 "content", 친구 추가라면 "friend" 들어오면 됨
    private String id;  //여기 댓글 업로드라면 해당 댓글 작성자가 누구인지, 댓글 수정이라면 "해당 댓글의 commentId" 게시물 업로드라면 작성자의 id, friend의 경우엔 추가하고 싶은 사람의 id
    private String content;//frined의 경우 여긴 null
}
