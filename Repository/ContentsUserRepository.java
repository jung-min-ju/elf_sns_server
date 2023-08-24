package ToyProject.SNS.Repository;

import ToyProject.SNS.Entity.ContentsUser;

import java.util.List;

public interface ContentsUserRepository {
    ContentsUser save(ContentsUser contentsUser);
    List<ContentsUser> findById(long id);
}
