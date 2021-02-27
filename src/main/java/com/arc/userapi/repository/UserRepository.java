package com.arc.userapi.repository;

import com.arc.userapi.Enums.Status;
import com.arc.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Charles on 27/02/2021
 */
public interface UserRepository extends JpaRepository<User, Long>  {

    @Query("select u from User u where u.status <> :status")
    List<User> findUsersNotInStatus(@Param("status") Status status);
}
