package com.arc.userapi.repository;

import com.arc.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Charles on 27/02/2021
 */
public interface UserRepository extends JpaRepository<User, Long>  {

    @Query("select u from User u where u.status <> 'Deactivated'")
    List<User> findAllActiveUsers();

    @Query("select u from User u where u.email = :email and u.status <> 'Deactivated'")
    User findUsersByEmail(@Param("email") String email);
}
