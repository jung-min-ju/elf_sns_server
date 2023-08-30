package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.Contents;
import ToyProject.SNS.Entity.ImageFile;
import ToyProject.SNS.Repository.ContentsBootRepository;
import ToyProject.SNS.Repository.ContentsRepository;
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
    private ImageFileRepository imageFileRepository;

    public ContentsService(UserService userService, ContentsRepository contentsRepository, ImageFileRepository imageFileRepository,
                           ContentsBootRepository contentsBootRepository) {
        this.userService = userService;
        this.contentsRepository = contentsRepository;
        this.imageFileRepository = imageFileRepository;
        this.contentsBootRepository = contentsBootRepository;
    }

    public void createContents(int seed, Long requiredPage){ //콘텐츠 생성

        String [] languge =  {"en", "fr", "ja", "ko", "zh", "ru"};

        List <Contents> create_Contents = new ArrayList<>();
        userService.createUser(seed);

        for(int i=0; i<requiredPage; i++) { //페이지 만들기

            Random random = new Random();
            int index = random.nextInt(6);
            String language = languge[index];
            int Seed = random.nextInt(1000) + 1;
            Faker faker = new Faker(new Locale(language), new Random(Seed)); //requredPage가 만들어질때마다, 랜덤의 언어로 컨텐츠 생성

            List<ImageFile> allImageFiles = imageFileRepository.findAll();
            int totalImageFiles = allImageFiles.size();

            Random Imagerandom = new Random();
            int ImagerandomIndex = Imagerandom.nextInt(totalImageFiles);

            ImageFile randomImageFile = allImageFiles.get(ImagerandomIndex);

            //faker로 contents 필드의 내용 생성
            Contents contents = new Contents();
            contents.setContentsId(UUID.randomUUID().toString()); // 컨텐츠id의 uuid 생성
            contents.setAuthorId(userService.getUserID("random")); //사실 이렇게 user의 uuid 값만 넘겨주는게 맞는지는 모르겟음
            contents.setCreateAt(Long.valueOf(Long.toString(faker.date().past(365, TimeUnit.DAYS).getTime())));
            contents.setImgUrl(randomImageFile.getFilePath());
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

}
