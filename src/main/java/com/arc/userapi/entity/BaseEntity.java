package com.arc.userapi.entity;

import com.arc.userapi.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.Date;

/**
 * @author Charles on 27/02/2021
 */
@Data
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_deactivated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeactivated;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
