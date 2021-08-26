package com.example.Telegram.Bot.repository;

import com.example.Telegram.Bot.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findModelByUserId(Long userId);
}