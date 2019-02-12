package model.entity;


import model.entity.api.AlertPreference;
import model.entity.cta.customeralerts.CtaAlertPreference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alerts_usr")
@Access(AccessType.FIELD)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
//    @NotNull
    private String email;


    @Column(name = "username")
//    @NotNull
    private String username;



    public Set<CtaAlertPreference> getNotificationList() {
        return notificationList;
    }


    public void setNotificationList(Set<CtaAlertPreference> notificationList) {
        this.notificationList = notificationList;
    }

    @OneToMany(targetEntity = AlertPreference.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<CtaAlertPreference> notificationList = new HashSet<>();



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public AppUser(long id, String email, String username, Set<CtaAlertPreference> notificationList) {
//        this.id = id;
//        this.email = email;
//        this.username = username;
////        this.notificationList = notificationList;
//    }


    public String getEmail() {
        return email;
    }

    public AppUser() {
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
