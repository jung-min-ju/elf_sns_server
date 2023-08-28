package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageFileBootRepository extends JpaRepository<ImageFile,Long>, ImageFileRepository {

}
