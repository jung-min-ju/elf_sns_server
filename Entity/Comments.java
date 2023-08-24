package ToyProject.SNS.Entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentId;
    private String userId; //이건 contestUser에서 faker 돌린 값 넣어줄 것임. 즉 이 부분만 faker로 돌리는게 아니라 넣어줄 것.
    private String createAt;
    private String comment;

}
/*
* comments: [{
                    commentId:"commentId_123412",
                    userId:"userId_123123",
                    createAt:1698469752808,
                    comment:"와 진짜 개공감 ㅇㅈ"
                }
                * */