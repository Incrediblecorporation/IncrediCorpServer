package incredicorpserver.repository;

import incredicorpserver.beans.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
    User findByUserId(int id);
    User findByUserMail(String userMail);
    User findByUserName(String userName);
    User findByUserMailAndUserPassword(String userMail,String userPassword);
    User findByUserMailAndUserName(String userMail,String userName);
}
