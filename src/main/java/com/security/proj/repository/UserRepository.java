package com.security.proj.repository;

import com.security.proj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {

    User findByUsername(String username);

    @Override
    List<User> findAll();

    @Override
    <S extends User> S saveAndFlush(S entity);

    void deleteByUsername(String username);
}