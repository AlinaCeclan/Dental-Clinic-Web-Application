package org.example.repositories;

import org.example.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {


    @Query("from UserDetails where user.username = ?1")
    UserDetails getIdByUsername(String username);

}
