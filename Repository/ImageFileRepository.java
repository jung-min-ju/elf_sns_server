package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.ImageFile;

import java.util.List;
import java.util.Optional;

public interface ImageFileRepository {
    ImageFile save(ImageFile imageFile);
    List<ImageFile> findAll();
}
