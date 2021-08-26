package com.example.Telegram.Bot.job;

import com.example.Telegram.Bot.model.Users;
import com.example.Telegram.Bot.bot.Bot;
import com.example.Telegram.Bot.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    UsersRepository repository;

    @Autowired
    Bot bot;

    @Autowired
    Users model ;

    @Override
    public void synchronizeTracking() {
        List<Users> userId = repository.findAll();
        userId.forEach(user->{
            bot.sendMessage(String.valueOf(user.getUserId()), "Time to drink water!");
        });
    }
}

