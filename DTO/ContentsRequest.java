package ToyProject.SNS.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentsRequest {
    private String sessionId;
    private String contentId;
    private Long requiredPage; //음수 또는 양수로 들어옴
}

/**
 * 만약 컨텐츠아이다가 널이고, 리카이얼드 페이지의 수만큼 컨텐츠를 주기
 * 만약 컨텐츠아이닥 있으면, 해당 id를 기준으로 required 페이지가 양수면 더 과거인 시간 순으로 10개 주기.
 *                              -10이면 해당 컨텐츠 아이디보다 더 최근인 페이지를 주면 됨
 * 컨텐츠아이디가 커질수록 최근게시물이라고 생각하기
 */
