package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.Contents;
import ToyProject.SNS.Entity.ImageFile;
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
    private ImageFileRepository imageFileRepository;

    public ContentsService(UserService userService, ContentsRepository contentsRepository, ImageFileRepository imageFileRepository) {
        this.userService = userService;
        this.contentsRepository = contentsRepository;
        this.imageFileRepository = imageFileRepository;
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
            contents.setAuthorId(userService.getUserID()); //사실 이렇게 user의 uuid 값만 넘겨주는게 맞는지는 모르겟음
            contents.setCteateAt(Long.valueOf(Long.toString(faker.date().past(365, TimeUnit.DAYS).getTime())));
            contents.setImgUrl(randomImageFile.getFilePath());
            contents.setContent(faker.lorem().paragraph());

            //create_Contents 리스트에 내용 추가
            create_Contents.add(contents);
            contentsRepository.save(contents);
        }
    }

    public List<Contents> returnOneContent(){
        List<Contents> allContents = contentsRepository.findAll();

        List<Contents> OneContents = new ArrayList<>();

        Random random = new Random();
        int ContentIndex = random.nextInt(allContents.size());

        OneContents.add(allContents.get(ContentIndex));
        return OneContents;
    }

}
