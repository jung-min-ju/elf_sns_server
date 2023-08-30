package ToyProject.SNS.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ContentsId;
    private String authorId;
    private Long createAt;
    private String imgUrl;
    @Column(columnDefinition = "TEXT") // 이 부분을 추가하여 데이터 타입을 변경
    private String content;
    private Long createAtID;

}
