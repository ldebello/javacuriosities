package ar.com.javacuriosities.websockets.timers;

import ar.com.javacuriosities.websockets.PriceEndpoint;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import java.util.Random;

@Startup
@Singleton
public class PriceVolumeBean {

    @Resource
    private TimerService timer;

    private Random random;
    private volatile double price = 100.0;
    private volatile int volume = 300000;

    @PostConstruct
    public void initialize() {
        random = new Random();
        timer.createIntervalTimer(1000, 1000, new TimerConfig());
    }

    @Timeout
    public void timeout() {
        price += 1.0 * (random.nextInt(100) - 50) / 100.0;
        volume += random.nextInt(5000) - 2500;
        PriceEndpoint.send(price, volume);
    }

    public TimerService getTimer() {
        return timer;
    }

    public void setTimer(TimerService timer) {
        this.timer = timer;
    }
}