package model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CTA_ALERTS_USR")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    @NotNull
    private String email;


    @Column(name = "username")
    @NotNull
    private String username;


//    @OneToMany(mappedBy = "user",targetEntity = CtaAlertPreference.class)
//    @JoinColumn(name = "id", referencedColumnName = "user_id")
//    public Set<CtaAlertPreference> getNotificationList() {
//        return notificationList;
//    }
//
//
//    public void setNotificationList(Set<CtaAlertPreference> notificationList) {
//        this.notificationList = notificationList;
//    }
//
//
//    private Set<CtaAlertPreference> notificationList;
//


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public User(long id, String email, String username, Set<CtaAlertPreference> notificationList) {
//        this.id = id;
//        this.email = email;
//        this.username = username;
////        this.notificationList = notificationList;
//    }


    public String getEmail() {
        return email;
    }

    public User() {
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }






}
