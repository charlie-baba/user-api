package com.arc.userapi.repository;

import com.arc.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Charles on 27/02/2021
 */
public interface UserRepository extends JpaRepository<User, Long>  {

}
