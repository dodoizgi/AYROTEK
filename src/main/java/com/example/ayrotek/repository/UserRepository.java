package com.example.ayrotek.repository;

import com.example.ayrotek.model.UserModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModule, Integer> {
    List<UserModule> findUserModuleByMailAndPassword(String mail, String password);
}
