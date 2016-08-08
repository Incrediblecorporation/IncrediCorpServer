package incredicorpserver.webservice;

import incredicorpserver.beans.User;
import incredicorpserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.Mail_Send;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/user")
public class User_Webservice {

    @Autowired
    UserRepository userRepo;

    //--POST NEW ACCOUNT-- returns User -
    @RequestMapping(value="/new",method= RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user = userRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //--GET ALL USERS-- returns List<User> -
    @RequestMapping(value="/getAll", method= RequestMethod.GET)
    public ResponseEntity<List> getAllUsers() {
        return new ResponseEntity<List>(userRepo.findAll(), HttpStatus.OK);
    }

    //--REGISTER USER-- returns User -
    @RequestMapping(value="/register", method=POST)
    public ResponseEntity<User> registerUser(@RequestBody User userToRegister) {
        System.out.println("NAME = "+ userToRegister.getUserName());
        System.out.println("MAIL = "+ userToRegister.getUserMail());
        System.out.println("PASSWORD = "+ userToRegister.getUserPassword());

        if (userRepo.findByUserMail(userToRegister.getUserMail()) == null) {
            if (userRepo.findByUserName(userToRegister.getUserName()) == null) {
                // --> ACCOUNT DO NOT EXISTS, CREATE ACCOUNT
                /*
                System.out.println("NAME = "+ userToRegister.getUserName());
                System.out.println("MAIL = "+ userToRegister.getUserMail());
                System.out.println("PASSWORD = "+ userToRegister.getUserPassword());
                */
                userToRegister = userRepo.save(userToRegister);
                return new ResponseEntity<>(userToRegister, HttpStatus.OK);
            }
            else {
                // --> MAIL DO NOT EXISTS
                userToRegister.setUserMail("NOK");
                return new ResponseEntity<>(userToRegister, HttpStatus.OK);
            }
        }
        else {
            if (userRepo.findByUserMailAndUserPassword(userToRegister.getUserMail(),userToRegister.getUserPassword()) == null) {
                // --> WRONG PASSWORD
                userToRegister.setUserPassword("NOK");
                return new ResponseEntity<>(userToRegister, HttpStatus.OK);
            }
            else {
                // --> LOG IN
                return new ResponseEntity<>(userRepo.findByUserMailAndUserPassword(userToRegister.getUserMail(),userToRegister.getUserPassword()), HttpStatus.OK);
            }
        }
        /*
        if (userRepo.findByUserMailAndUserPassword(user.getUserMail(),user.getUserPassword()) == null) {
            if (userRepo.findByUserMailAndUserName(user.getUserMail(),user.getUserName()) == null){
                // --> ACCOUNT DO NOT EXISTS, CREATE ACCOUNT
                System.out.println("NAME = "+ user.getUserName());
                System.out.println("MAIL = "+ user.getUserMail());
                System.out.println("PASSWORD = "+ user.getUserPassword());
                User newUser = userRepo.save(user);
                return new ResponseEntity<>(newUser, HttpStatus.OK);
            } else if (userRepo.findByUserMail(user.getUserMail()) == null) {
                // --> MAIL DO NOT EXISTS
                user.setUserMail("NOK");
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                // --> WRONG PASSWORD
                user.setUserPassword("NOK");
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        } else {
            // --> LOG IN
            return new ResponseEntity<>(userRepo.findByUserMailAndUserPassword(user.getUserMail(),user.getUserPassword()), HttpStatus.OK);
        }
        */
    }

    //--LOG USER-- returns User -
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public ResponseEntity<User> logUser(@RequestBody User user) {
        if (userRepo.findByUserMailAndUserPassword(user.getUserMail(),user.getUserPassword()) != null) {
            // --> LOG IN
            return new ResponseEntity<>(userRepo.findByUserMailAndUserPassword(user.getUserMail(),user.getUserPassword()), HttpStatus.OK);
        } else {
            // --> WRONG PASSWORD
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    //--GET BACK PASSWORD-- returns Boolean -
    @RequestMapping(value="/getBackPassword", method=RequestMethod.POST)
    public ResponseEntity<Boolean> sendLogInInfos(@RequestBody String mail) {
        if (userRepo.findByUserMail(mail)!=null) {
            // --> SEND LOG IN INFOS
            String sendMailConfFile = "sendMail_Bean.xml";
            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(sendMailConfFile);
            Mail_Send sendMailAPI = (Mail_Send) context.getBean("sendMail");
            sendMailAPI.sendMailToUser(userRepo.findByUserMail(mail));
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            // --> MAIL DO NOT EXISTS
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

    //--CHECK NAME-- returns Boolean -
    @RequestMapping(value = "/checkName", method=RequestMethod.POST)
    public ResponseEntity<Boolean> checkName(@RequestBody String name) {
        if (userRepo.findByUserName(name)!=null) {
            // --> NAME ALREADY EXISTS
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        // --> NAME DO NOT EXISTS
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    //--CHECK MAIL-- returns Boolean -
    @RequestMapping(value = "/checkMail", method=RequestMethod.POST)
    public ResponseEntity<Boolean> checkMail(@RequestBody String mail) {
        if (userRepo.findByUserMail(mail)!=null) {
            // --> MAIL ALREADY EXISTS
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        // --> MAIL DO NOT EXISTS
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
