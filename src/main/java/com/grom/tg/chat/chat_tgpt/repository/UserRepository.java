package com.grom.tg.chat.chat_tgpt.repository;

import com.grom.tg.chat.chat_tgpt.entity.MyUser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    boolean existsByUserId(Long id);

    MyUser findByUserName(String username);

    @Cacheable(value = "users", key = "#userId")
    MyUser findByUserId(Long userId);

    @CacheEvict(value = "users", key = "#userId")
    public void deleteByUserId(Long userId);
}
