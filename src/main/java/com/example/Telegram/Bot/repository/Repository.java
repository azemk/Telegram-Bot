package com.example.Telegram.Bot.repository;

import com.example.Telegram.Bot.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Model,Long> {
    Model findModelByUserId(Long userId);
}
