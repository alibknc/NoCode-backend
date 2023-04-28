package com.kou.bitirme.data.repository;

import com.kou.bitirme.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select e from User e where e.email = ?1 and e.password = ?2")
    User getUser(String email, String password);

}
