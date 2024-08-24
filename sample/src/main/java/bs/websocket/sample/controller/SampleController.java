package bs.websocket.sample.controller;

import bs.websocket.sample.application.dto.SampleRequestDTO;
import bs.websocket.sample.application.service.SampleService;
import bs.websocket.sample.application.dto.SampleResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Log4j2(topic = "Sample Controller")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @MessageMapping("/sample") // "/sample"은 클라이언트가 메시지를 보낼 때 사용하는 경로
    @SendTo("/topic/samples") // 메서드의 반환값을 특정 주제로 보내도록 지정
    public List<SampleResponseDTO> getSamples(){

        log.info("SampleController : GET - getSamples");

        log.info(sampleService.getSampleList());
        return sampleService.getSampleList();
    }

    @GetMapping("/sample/list")
    public void getSampleList(Model model){

        log.info("SampleController : GET - getSampleList");

        List<SampleResponseDTO> sampleList = sampleService.getSampleList();

        model.addAttribute("sampleList", sampleList);
    }

    @PostMapping("/sample")
    public ResponseEntity<String> createSample (@RequestBody SampleRequestDTO sampleRequestDTO){

        log.info("SampleController : POST - createSample");

        sampleService.createSample(sampleRequestDTO);
        return ResponseEntity.ok("Create Success");
    }

    @GetMapping("/sample/{id}")
    public ResponseEntity<SampleResponseDTO> getSampleById(@PathVariable String id){

        log.info("SampleController : GET - getSampleById");

        SampleResponseDTO sample = sampleService.getSampleById(id);
        return ResponseEntity.ok(sample);
    }

    @PutMapping("/sample/{id}")
    public ResponseEntity<String> updateSample(@PathVariable String id, @RequestBody SampleRequestDTO sampleRequestDTO){

        log.info("SampleController : PUT - updateSample");

        sampleService.updateSample(id,sampleRequestDTO);
        return ResponseEntity.ok("Update Success");
    }

    @DeleteMapping("/sample/{id}")
    public ResponseEntity<String> deleteSample(@PathVariable String id){

        log.info("SampleController : DELETE - deleteSample");

        sampleService.deleteSample(id);
        return ResponseEntity.ok("Delete Success");
    }

}
