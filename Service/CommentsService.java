package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.Comments;
import ToyProject.SNS.Entity.ImageFile;
import ToyProject.SNS.Repository.CommentsBootRepository;
import ToyProject.SNS.Repository.CommentsRepository;
import ToyProject.SNS.Repository.ContentsBootRepository;
import ToyProject.SNS.Repository.ImageFileBootRepository;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class CommentsService {


    private CommentsRepository commentsRepository;
    private UserService userService;
    private ImageFileBootRepository imageFileBootRepository;
    private CommentsBootRepository commentsBootRepository;

    public CommentsService(CommentsRepository commentsRepository, UserService userService,
                           ImageFileBootRepository imageFileBootRepository, CommentsBootRepository commentsBootRepository) {
        this.commentsRepository = commentsRepository;
        this.userService = userService;
        this.imageFileBootRepository = imageFileBootRepository;
        this.commentsBootRepository = commentsBootRepository;
    }


    public void createComments() {

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
            comments.setUserId(userService.getUserID("random"));
            comments.setCreateAt(Long.valueOf(Long.toString(faker.date().past(365, TimeUnit.DAYS).getTime())));
            comments.setComment(faker.lorem().sentence());

            Optional<ImageFile> imageFile = imageFileBootRepository.getRandomImageFile();
            comments.setImgUrl(imageFile.get().getFilePath());

            commentsList.add(comments);
        }
        commentsBootRepository.saveAll(commentsList);
    }

    public void addComment(String commentId, String text){
        Optional<Comments> comment = commentsBootRepository.findBycommentId(commentId);
    }

    public void editComment(String commentId, String text){
        Optional<Comments> comment = commentsBootRepository.findBycommentId(commentId);
        if(comment.isPresent()){
            Comments uploadcomment = comment.get();
            uploadcomment.setComment(text);
            commentsRepository.save(uploadcomment);
        }
    }

    public void deleteComment(String commentId){
        Optional<Comments> comment = commentsBootRepository.findBycommentId(commentId);
        comment.ifPresent(commentsBootRepository::delete);
    }

}
