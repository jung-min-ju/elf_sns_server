package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Contents;

import java.util.List;
import java.util.Optional;

public interface ContentsRepository {
    Contents save(Contents contents);
    Optional<Contents> findById(Long id); //null 값 존재할 수 있으니 Optional 사용
    List <Contents> findAll();
}
