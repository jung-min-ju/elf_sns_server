package ToyProject.SNS.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadDTO {
    private String standard; //여기 댓글 업로드라면 "comment", 게시물 업로드라면 "content", 친구 추가라면 "friend" 들어오면 됨
    private String Userid;
    private String content;//frined의 경우 여긴 null
}
