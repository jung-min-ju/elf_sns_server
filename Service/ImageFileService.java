package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.ImageFile;
import ToyProject.SNS.Repository.ImageFileRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class ImageFileService {

    private final ResourceLoader resourceLoader;
    private final ImageFileRepository imageFileRepository;

    public ImageFileService(ResourceLoader resourceLoader, ImageFileRepository imageFileRepository) {
        this.resourceLoader = resourceLoader;
        this.imageFileRepository = imageFileRepository;
    }

    public void findByImagesFiles() {
        // 이미지 파일 경로
        String imageDirectoryPath = "classpath:/static/Images/";
        try {
            Resource resource = resourceLoader.getResource(imageDirectoryPath);
            File imageDirectory = resource.getFile();

            File[] imageFiles = imageDirectory.listFiles((dir, name) -> name.endsWith(".jpg"));

            for (File imageFile : imageFiles) {
                ImageFile entity = new ImageFile();
                entity.setFileName(imageFile.getName());
                entity.setFilePath("/Images/" + imageFile.getName());
                imageFileRepository.save(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ImageFile> returnImages(){
        List<ImageFile> allContents = imageFileRepository.findAll();

        List<ImageFile> OneImages = new ArrayList<>();

        Random random = new Random();
        int response_random_dex = random.nextInt(5)+1;

        for(int i=0; i<response_random_dex; i++){
            int ImageIndex = random.nextInt(allContents.size());
            OneImages.add(allContents.get(ImageIndex));
        }
        return OneImages;
    }

}