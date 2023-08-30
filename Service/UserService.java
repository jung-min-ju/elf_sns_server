package ToyProject.SNS.Service;

import ToyProject.SNS.Entity.ContentsUser;
import ToyProject.SNS.Entity.Friend;
import ToyProject.SNS.Entity.ImageFile;
import ToyProject.SNS.Repository.*;
import com.github.javafaker.Faker;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private ContentsUserBootRepository contentsUserBootRepository;
    private ContentsUserRepository contentsUserRepository;
    private FriendBootRepository friendBootRepository;

    public UserService(ContentsUserBootRepository contentsUserBootRepository, ContentsUserRepository contentsUserRepository,
                       FriendBootRepository friendBootRepository) {
        this.contentsUserBootRepository = contentsUserBootRepository;
        this.contentsUserRepository = contentsUserRepository;
        this.friendBootRepository = friendBootRepository;
    }

    private Long UserCount = 50L;

    public void createUser(){
        List<ContentsUser> users = new ArrayList<>();

        for(int i=0; i<UserCount; i++){ //user 1000명으로 잡기
            UUID uuid = UUID.randomUUID();
            Faker faker = new Faker();
            ContentsUser user = new ContentsUser();
            user.setUserID(uuid.toString());
            user.setName(faker.name().name());

            users.add(user);
        }
        contentsUserBootRepository.saveAll(users);
    }

    public void addUser(String userName){

        UUID uuid = UUID.randomUUID();
        Faker faker = new Faker();

        ContentsUser user = new ContentsUser();
        user.setUserID(uuid.toString());
        user.setName(userName);

        contentsUserRepository.save(user);
        UserCount++;
    }

    public String getUserID(String standard) {  //댓글에서 userId 설정 시 사용해줄 랜덤 userId

        Random random = new Random();
        long user_index = 0;

        if(standard.equals("random")) {
            user_index = random.nextLong(UserCount) + 1;
        }
        else {
            user_index = Long.parseLong(standard);
        }

        Optional<ContentsUser> userOptional = contentsUserRepository.findById(user_index);

        if (userOptional.isPresent()) {
            ContentsUser user = userOptional.get();
            return user.getUserID();
        } else {
            // 사용자 정보가 없을 경우 처리
            return "No UserID Available";
        }
    }

    public void createFriendship() {
        List<Friend> friendsList = new ArrayList<>();
        Random random = new Random();
        int friendIndex = -1;

        for (int i = 1; i <= UserCount; i++) {

            do {
                friendIndex = random.nextInt(Math.toIntExact(UserCount)) + 1;
            } while (friendIndex == i);

            String UserUUID = getUserID(String.valueOf(i));
            String FriendUUID = getUserID(String.valueOf(friendIndex));

            if(CheckExistsFriendShip(UserUUID, FriendUUID).equals("NotExits")){
                //나 -> 친구 저장
                Friend User = new Friend();
                User.setUser_uuid(UserUUID);
                User.setFriend_uuid(FriendUUID);

                //친구 -> 나 저장
                Friend friend = new Friend();
                friend.setUser_uuid(FriendUUID);
                friend.setFriend_uuid(UserUUID);

                friendsList.add(User);
                friendsList.add(friend);
            }

        }
        friendBootRepository.saveAll(friendsList);
    }

    public String CheckExistsFriendShip(String UserUUID, String FriendUUID){
        Set<String> uniqueFriendships = new HashSet<>();

        Friend AtoB = new Friend();
        Friend BToA = new Friend();
        AtoB.setFriend_uuid(UserUUID+"-"+FriendUUID);
        BToA.setFriend_uuid(FriendUUID+"-"+UserUUID);

        if (!uniqueFriendships.contains(AtoB.getFriend_uuid()) && !uniqueFriendships.contains(BToA.getFriend_uuid())) {
            uniqueFriendships.add(AtoB.getFriend_uuid());
            uniqueFriendships.add(BToA.getFriend_uuid());
            return "NotExits";
        }
        return "Exits";
    }

    public void uploadFriend(){
        //친구 관계 확인하기
        //각각의 uuid 가져오기
        //추가하기
    }

}
