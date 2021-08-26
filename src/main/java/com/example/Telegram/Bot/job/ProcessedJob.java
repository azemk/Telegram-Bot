package com.example.Telegram.Bot.job;

import com.example.Telegram.Bot.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessedJob {
    @Autowired
    JobService jobService;

    @Scheduled(cron = "0 */10 * * * * ")
    public void processed(){

        jobService.synchronizeTracking( );
    }


}
