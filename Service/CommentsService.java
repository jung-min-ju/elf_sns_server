package ToyProject.SNS.Service;

import ToyProject.SNS.Repository.CommentsRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }


}
