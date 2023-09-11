package com.developer.etscase.repository;

import com.developer.etscase.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "    SELECT  *\n" +
            "FROM    tbl_users tu\n" +
            "WHERE   (cast(:username as text) is null or tu.username = (cast(:username as text)))\n" +
            "AND     (cast(:password as text) is null or tu.password = (cast(:password as text))) "
            ,nativeQuery = true)
    UserEntity getUser(String username, String password);

    @Query(value = "    SELECT  *\n" +
            "FROM    tbl_users tu\n" +
            "WHERE   (cast(:username as text) is null or tu.username = (cast(:username as text))) "
            ,nativeQuery = true)
    UserEntity findUserByUsername(String username);
}
