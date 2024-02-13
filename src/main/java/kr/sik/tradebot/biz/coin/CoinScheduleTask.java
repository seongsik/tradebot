package kr.sik.tradebot.biz.coin;


import kr.sik.tradebot.biz.coin.service.CoinApiService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class CoinScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(CoinScheduleTask.class);

    private final CoinApiService coinApiService;

    @Scheduled(fixedDelay = 5000)
    public void run() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
