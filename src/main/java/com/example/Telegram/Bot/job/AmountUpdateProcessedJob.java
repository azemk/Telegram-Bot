package com.example.Telegram.Bot.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class AmountUpdateProcessedJob {


    @Autowired
    AmountUpdateService service;


    @Scheduled(cron = "0 */5 * * * *" )
    public void updateAmount(){
      service.synchronizeAmount();
    }






}
