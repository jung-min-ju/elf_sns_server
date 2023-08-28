package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.ContentsUser;
import ToyProject.SNS.Repository.ContentsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private ContentsUserRepository contentsUserRepository;

    public UserService(ContentsUserRepository contentsUserRepository) {
        this.contentsUserRepository = contentsUserRepository;
    }


    public void createUser(int seed){

        for(int i=0; i<1000; i++){ //user 1000명으로 잡기
            UUID uuid = UUID.randomUUID();

            ContentsUser user = new ContentsUser();
            user.setUserID(uuid.toString());

            contentsUserRepository.save(user);
        }
    }

    public String getUserID() {
        Random random = new Random();
        long user_index = random.nextInt(1000) + 1;
        Optional<ContentsUser> userOptional = contentsUserRepository.findById(user_index);

        if (userOptional.isPresent()) {
            ContentsUser user = userOptional.get();
            return user.getUserID();
        } else {
            // 사용자 정보가 없을 경우 처리
            return "No UserID Available";
        }
    }

}
