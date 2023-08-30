package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.Contents;
import ToyProject.SNS.Entity.ImageFile;
import ToyProject.SNS.Repository.ContentsBootRepository;
import ToyProject.SNS.Repository.ContentsRepository;
import ToyProject.SNS.Repository.ImageFileBootRepository;
import ToyProject.SNS.Repository.ImageFileRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ContentsService {
    private UserService userService;

    private ContentsRepository contentsRepository;
    private ContentsBootRepository contentsBootRepository;
    private ImageFileBootRepository imageFileBootRepository;

    public ContentsService(UserService userService, ContentsRepository contentsRepository, ImageFileBootRepository imageFileBootRepository,
                           ContentsBootRepository contentsBootRepository) {
        this.userService = userService;
        this.contentsRepository = contentsRepository;
        this.imageFileBootRepository = imageFileBootRepository;
        this.contentsBootRepository = contentsBootRepository;
    }

    public void createContents(int seed, Long requiredPage){ //콘텐츠 생성

        String [] languge =  {"en", "fr", "ja", "ko", "zh", "ru"};

        List <Contents> create_Contents = new ArrayList<>();

        for(int i=0; i<requiredPage; i++) { //페이지 만들기

            Random random = new Random();
            int index = random.nextInt(6);
            String language = languge[index];
            int Seed = random.nextInt(1000) + 1;
            Faker faker = new Faker(new Locale(language), new Random(Seed)); //requredPage가 만들어질때마다, 랜덤의 언어로 컨텐츠 생성

            Optional<ImageFile> imageFile = imageFileBootRepository.getRandomImageFile();

            //faker로 contents 필드의 내용 생성
            Contents contents = new Contents();
            contents.setContentsId(UUID.randomUUID().toString()); // 컨텐츠id의 uuid 생성
            contents.setAuthorId(userService.getUserID("random")); //사실 이렇게 user의 uuid 값만 넘겨주는게 맞는지는 모르겟음
            contents.setCreateAt(Long.valueOf(Long.toString(faker.date().past(365, TimeUnit.DAYS).getTime())));
            contents.setImgUrl(imageFile.get().getFilePath());
            contents.setContent(faker.lorem().paragraph());

            create_Contents.add(contents);
        }

        // 모든 내용을 저장
        contentsBootRepository.saveAll(create_Contents);

        // Sort the contents by createAt
        List<Contents> sortedContents = contentsBootRepository.findAllByOrderByCreateAtAsc();

        // Set createAtID for each content
        Long nextId = 1L;
        for (Contents content : sortedContents) {
            content.setCreateAtID(nextId);
            nextId++;
        }

        // Update contents with createAtID
        contentsBootRepository.saveAll(sortedContents);
    }

    public void uploadContent(String writerId, String text){

        Optional<ImageFile> imageFile = imageFileBootRepository.getRandomImageFile();

        Contents contents = new Contents();
        contents.setContentsId(UUID.randomUUID().toString()); // 컨텐츠id의 uuid 생성
        contents.setAuthorId(userService.getUserID(writerId));
        contents.setCreateAt(System.currentTimeMillis());
        contents.setImgUrl(imageFile.get().getFilePath());
        contents.setContent(text);

    }

}
