package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.ImageFile;

import java.util.List;
import java.util.Optional;

public interface ImageFileRepository {
    ImageFile save(ImageFile imageFile);
    List<ImageFile> findAll();
    Optional<ImageFile> findById(Long id); //null 값 존재할 수 있으니 Optional 사용
}
