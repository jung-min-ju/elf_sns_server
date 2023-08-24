package ToyProject.SNS.DTO;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginDTO {
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotEmpty(message = "password cannot be empty")
    private String password;

}
