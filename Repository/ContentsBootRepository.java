package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContentsBootRepository extends JpaRepository<Contents, Long>, ContentsRepository{
    List<Contents> findAllByOrderByCreateAtAsc();
    Optional<Contents> findByCreateAtID(Long createAtID);

}
