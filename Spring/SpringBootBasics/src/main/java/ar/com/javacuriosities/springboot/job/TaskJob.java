package ar.com.javacuriosities.springboot.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskJob {

    @Scheduled(fixedDelay = 5000)
    public void taskJob() {
        System.out.println("***** Task Job *****");
        System.out.println("Scheduled Job");
        System.out.println("********************");
    }
}
