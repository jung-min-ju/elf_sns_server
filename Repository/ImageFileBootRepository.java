package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ImageFileBootRepository extends JpaRepository<ImageFile,Long>, ImageFileRepository {
    @Query(value = "SELECT * FROM image_file ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<ImageFile> getRandomImageFile();
}
