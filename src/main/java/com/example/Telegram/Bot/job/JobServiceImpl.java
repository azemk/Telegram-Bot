package com.example.Telegram.Bot.job;


import com.example.Telegram.Bot.model.Model;
import com.example.Telegram.Bot.bot.Bot;
import com.example.Telegram.Bot.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobServiceImpl implements JobService {

    @Autowired
    Repository repository;

    @Autowired
    Bot bot;

    @Autowired
    Model model ;


    @Override
    public void synchronizeTracking() {
        List<Model> userId = repository.findAll();
        userId.forEach(user->{
            bot.sendMessage(String.valueOf(user.getUserId()), "Time to drink water!");
        });
    }
}

