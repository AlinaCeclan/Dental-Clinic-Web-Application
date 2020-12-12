package org.example.repositories;

import org.example.entities.User;
import org.example.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    @Query(" FROM User user WHERE user.userDetails.userTypes.userTypeName  = ?1 and user.deleted = false")
    List<User> getUsersList( String userType);

}
