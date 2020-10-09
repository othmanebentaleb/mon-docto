package com.mondocto.ms.repository;

import com.mondocto.ms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
