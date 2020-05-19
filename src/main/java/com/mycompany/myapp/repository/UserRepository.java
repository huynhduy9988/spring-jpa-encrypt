package com.mycompany.myapp.repository;

import com.mycompany.myapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findOneByGmailAddress(String gmail);
}
