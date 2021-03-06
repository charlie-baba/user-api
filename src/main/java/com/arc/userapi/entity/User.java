package com.arc.userapi.entity;

import com.arc.userapi.enums.Role;
import com.arc.userapi.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.Date;

/**
 * @author Charles on 27/02/2021
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "date_registered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered = new Date();

    @Column(name = "date_verified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVerified;

    @Column(name = "is_verified")
    private boolean verified = false;

    @Column(name = "verification_code")
    private String verificationCode;

    public User() { }

    public User(Long id, String title, String firstName, String lastName, String email, String mobile, String password,
                Role role, Date dateRegistered, Date dateVerified, boolean verified, Date dateDeactivated, Status status) {
        this.setId(id);
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
        this.dateRegistered = dateRegistered;
        this.dateVerified = dateVerified;
        this.verified = verified;
        this.setDateDeactivated(dateDeactivated);
        this.setStatus(status);
    }
}
