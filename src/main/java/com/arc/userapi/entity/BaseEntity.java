package com.arc.userapi.entity;

import com.arc.userapi.Enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
