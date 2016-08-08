package incredicorpserver.beans;

/**
 * Created by olivier on 08/12/2015.
 */

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    public User(String userName, String userMail, String userPassword) {
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="user_id")
    private int userId;

    @Column(name="user_mail")
    private String userMail;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_password")
    private String userPassword;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() { return userMail; }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() { return userPassword; }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}