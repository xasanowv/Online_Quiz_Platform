package com.company.userresponse;

import com.company.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponseEntity, UUID> {

}
