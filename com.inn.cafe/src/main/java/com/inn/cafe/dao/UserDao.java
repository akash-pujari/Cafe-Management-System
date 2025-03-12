package com.inn.cafe.dao;

import com.inn.cafe.pojo.User;
import com.inn.cafe.wrapper.UserWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmailId(@Param("email") String email);

    @Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
    @Transactional
    @Modifying
    void updateUserPassword(@Param("password") String password, @Param("email") String email);

    @Query("SELECT new com.inn.cafe.wrapper.UserWrapper(u.id, u.name, u.email, u.contactNumber, u.role,u.status) FROM User u WHERE u.role = 'user'")
    @Transactional
    @Modifying
    List<UserWrapper> getAllUser();

    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    @Transactional
    @Modifying
    void updateUserStatus(Integer id, String status);

    @Query("SELECT u.email FROM User u WHERE u.role = 'admin'")
    List<String> getAllAdmins();
}
