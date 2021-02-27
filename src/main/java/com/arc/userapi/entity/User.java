package com.arc.userapi.entity;

import com.arc.userapi.Enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
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
}
