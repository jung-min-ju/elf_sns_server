package ToyProject.SNS.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberShipDTO {

    @NotEmpty(message = "email cannot be empty")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    private String password;

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @Column(name = "phone_number")
    @NotEmpty(message = "phoneNumber cannot be empty")
    private String phoneNumber;

}
