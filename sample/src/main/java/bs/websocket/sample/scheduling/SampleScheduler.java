package bs.websocket.sample.scheduling;

import bs.websocket.sample.application.dto.SampleResponseDTO;
import bs.websocket.sample.application.service.SampleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@Log4j2
public class SampleScheduler {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 10000)
    public void sendUpdates() {
        List<SampleResponseDTO> samples = sampleService.getSampleList();
        if (samples.isEmpty()) {
            log.warn("No samples found in the database.");
        } else {
            log.info("Sending samples to clients: {}", samples);
        }
        messagingTemplate.convertAndSend("/topic/samples", samples);
    }
}
