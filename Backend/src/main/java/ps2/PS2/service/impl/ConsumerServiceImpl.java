package ps2.PS2.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerServiceImpl {
    public void consumeMessage(String messageBody) {
        log.info("Consumed Message: " + messageBody);
    }
}
