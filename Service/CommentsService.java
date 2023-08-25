package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.Comments;
import ToyProject.SNS.Repository.CommentsRepository;
import com.github.javafaker.Faker;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomStringUtils.random;

@Service
public class CommentsService {


    private CommentsRepository commentsRepository;
    private UserService userService;

    public CommentsService(CommentsRepository commentsRepository, UserService userService) {
        this.commentsRepository = commentsRepository;
        this.userService = userService;
    }


    public List<Comments> createComments() {

        String[] Comments_languge = {"en", "fr", "ja", "ko", "zh", "ru"};
        List<Comments> commentsList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {

            Random random = new Random();
            int index = random.nextInt(6);
            String language = Comments_languge[index];
            int seed = random.nextInt(1000) + 1;

            Faker faker = new Faker(new Locale(language), new Random(seed));
            Comments comments = new Comments();
            comments.setCommentId(UUID.randomUUID().toString());
            comments.setUserId(userService.getUser().toString());
            comments.setCreateAt(Long.toString(faker.date().past(365, TimeUnit.DAYS).getTime()));
            comments.setComment(faker.lorem().sentence());

            commentsList.add(comments);
            commentsRepository.save(comments);

        }
        return commentsList;
    }
}
