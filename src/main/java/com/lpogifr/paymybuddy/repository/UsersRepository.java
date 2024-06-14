package com.lpogifr.paymybuddy.repository;

import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.UserModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
  List<UserEntity> findAll();

  UserEntity findByEmail(String email);

  UserEntity save(UserEntity entity);

  @Query(value = "delete from UserEntity where email = ?1")
  void deleteByEmail(String email);

  UserEntity findByName(String name);

  @Query(
    value = "select * from USERS where id not in (select friend_id from friend where user_id = ?1) AND id <> ?1",
    nativeQuery = true
  )
  List<UserEntity> findOtheUser(Long userId);
}
