package com.floxcon.project.caver.fccabApp.repositories;

import com.floxcon.project.caver.fccabApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
