package com.hmss.springbootserver.repositories;

import com.hmss.springbootserver.entities.Patient;
import com.hmss.springbootserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN FETCH u.fileMetadataList f WHERE u.id = :userId AND f.type = 'PROFILE_IMAGE'")
    Optional<User> findUserAndProfileImage(@Param("userId") int userId);
}
