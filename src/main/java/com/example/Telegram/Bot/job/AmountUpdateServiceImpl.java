package com.example.Telegram.Bot.job;


import com.example.Telegram.Bot.model.Users;
import com.example.Telegram.Bot.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AmountUpdateServiceImpl  implements  AmountUpdateService{

    @Autowired
    UsersRepository repository;

    @Override
    public void synchronizeAmount() {
        List<Users> update = repository.findAll();
        update.forEach(user->{
            user.setAmount(0);
            repository.save(user);
        });

    }
}
