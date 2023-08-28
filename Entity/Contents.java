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

    public String ContentsId;
    public String authorId;
    public Long cteateAt;
    public String imgUrl;
    @Column(columnDefinition = "TEXT") // 이 부분을 추가하여 데이터 타입을 변경
    public String content;

}

/*
* id: //콘텐츠 아이디 돌려주면 됨
    author: { //저자
        id: "userId_12312", //저자 아이디
                imgUrl: "../img/test_img/사람_1.jpg" //저자 프로필 사진
    },
    createAt: 1692706863808, //숫자로
    imgUrl: ["../img/test_img/사람_5.jpg","../img/test_img/사람_6.jpg","../img/test_img/사람_7.jpg","../img/test_img/사람_8.jpg"], // 이미지 url
    content: "하루 죙일 버튜얼라이즈 인피니티로드를 만졌다. 메인 컨텐츠 크기를 동적으로 만들었다. 동적으로 안만들었으면 이렇게 고생할 일 없었을 텐데, 하 진짜 너무 짜증난다. //내용
**/
