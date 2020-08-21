package com.tekupstage.stageapp.repository;

import com.tekupstage.stageapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    User findByUsername(String userName);
}
