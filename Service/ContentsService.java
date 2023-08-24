package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.Contents;
import ToyProject.SNS.Repository.ContentsRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ContentsService {

    private UserService userService;

    private ContentsRepository contentsRepository;

    public ContentsService(UserService userService, ContentsRepository contentsRepository) {
        this.userService = userService;
        this.contentsRepository = contentsRepository;
    }

    public List<Contents> createContents(int seed, Long requiredPage){ //콘텐츠 생성

        String [] languge = {"en", "fr", "de", "es", "it", "ja", "ko", "zh", "pt", "ru"};

        List <Contents> create_Contents = new ArrayList<>();
        userService.createUser(seed);

        for(int i=0; i<requiredPage; i++) { //페이지 만들기

            Random random = new Random();
            int index = random.nextInt(10);
            String language = languge[index];
            Faker faker = new Faker(new Locale(language), new Random(seed)); //requredPage가 만들어질때마다, 랜덤의 언어로 컨텐츠 생성

            //faker로 contents 필드의 내용 생성
            Contents contents = new Contents();
            contents.setContentsid(UUID.randomUUID().toString()); // 컨텐츠id의 uuid 생성
            contents.setAuthor(userService.getUser().toString()); //사실 이렇게 user의 uuid 값만 넘겨주는게 맞는지는 모르겟음
            contents.setCteateAt(Long.toString(faker.date().past(365, TimeUnit.DAYS).getTime()));
            contents.setContent(faker.lorem().sentence());

            //create_Contents 리스트에 내용 추가
            create_Contents.add(contents);
            contentsRepository.save(contents);
        }
        return create_Contents;
    }

    //public List<Contents> getContents(){
    //    return
    //}

}
