package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.ContentsUser;
import ToyProject.SNS.Repository.ContentsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private ContentsUserRepository contentsUserRepository;

    public UserService(ContentsUserRepository contentsUserRepository) {
        this.contentsUserRepository = contentsUserRepository;
    }


    public void createUser(int seed){

        for(int i=0; i<1000; i++){ //user 1000명으로 잡기
            String uuid = UUID.randomUUID().toString();

            ContentsUser user = new ContentsUser();
            user.setUserID(uuid);

            contentsUserRepository.save(user);
        }
    }

    public List<ContentsUser> getUser(){
        Random random = new Random();
        long user_index = random.nextInt(1000)+1;
        return contentsUserRepository.findById(user_index);
    }

}
