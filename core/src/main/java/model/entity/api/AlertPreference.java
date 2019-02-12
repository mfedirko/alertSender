package model.entity.api;

import model.entity.AppUser;

import javax.persistence.*;

@Entity
@Table(name = "alrt_pref")
@Inheritance( strategy = InheritanceType.JOINED)
public class AlertPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name="prevt_dupl_ntfy")
    private boolean preventDuplicateNotifications;


    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @ManyToOne(targetEntity = AppUser.class)
    private AppUser user;

    public boolean isPreventDuplicateNotifications() {
        return preventDuplicateNotifications;
    }

    public void setPreventDuplicateNotifications(boolean preventDuplicateNotifications) {
        this.preventDuplicateNotifications = preventDuplicateNotifications;
    }
}
