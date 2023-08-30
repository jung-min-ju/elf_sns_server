package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageFileBootRepository extends JpaRepository<ImageFile,Long>, ImageFileRepository {

}
