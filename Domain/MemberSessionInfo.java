package ToyProject.SNS.Domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MemberSessionInfo implements Serializable {
    private String id;
    private String email;
    private String password;
}
