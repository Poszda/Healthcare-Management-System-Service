package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailAndPassword(String email, String password);
    @Query(value = "SELECT u.* FROM users u", nativeQuery = true)
    List<User> findAllUsers2();


}
