package bs.websocket.sample.application.service;

import bs.websocket.sample.application.dto.SampleRequestDTO;
import bs.websocket.sample.application.dto.SampleResponseDTO;
import bs.websocket.sample.domain.document.Sample;
import bs.websocket.sample.domain.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * 전체 조회
     * @return List<SampleResponseDTO>
     */
    @Override
    public List<SampleResponseDTO> getSampleList() {

        List<Sample> sampleList = sampleRepository.findAllByIsDeleteFalse();

        return sampleList.stream().map(SampleResponseDTO::new).toList();
    }

    /**
     * 샘플 생성
     * @param sampleRequestDTO
     */
    @Override
    public void createSample(SampleRequestDTO sampleRequestDTO) {

        Sample sample = Sample.builder()
                .name(sampleRequestDTO.getName())
                .description(sampleRequestDTO.getDescription())
                .build();

        sampleRepository.save(sample);

        // 모든 클라이언트에게 실시간으로 업데이트된 리스트 전송
        List<SampleResponseDTO> updatedSampleList = getSampleList();
        messagingTemplate.convertAndSend("/topic/samples", updatedSampleList);
    }

    /**
     * id로 조회
     * @param id
     * @return
     */
    @Override
    public SampleResponseDTO getSampleById(String id) {

        Sample sample = sampleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Sample not found id"));
        return new SampleResponseDTO(sample);
    }

    /**
     * 샘플 수정
     * @param id
     * @param sampleRequestDTO
     */
    @Override
    public void updateSample(String id, SampleRequestDTO sampleRequestDTO) {

        Sample sample = sampleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Sample not found id"));

        sample.setName(sampleRequestDTO.getName());
        sample.setDescription(sampleRequestDTO.getDescription());

        sampleRepository.save(sample);

        // 모든 클라이언트에게 실시간으로 업데이트된 리스트 전송
        List<SampleResponseDTO> updatedSampleList = getSampleList();
        messagingTemplate.convertAndSend("/topic/samples", updatedSampleList);

    }

    /**
     * 샘플 삭제
     * @param id
     */
    @Override
    public void deleteSample(String id) {

        Sample sample = sampleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Sample not found id"));

        sample.delete();
        sampleRepository.save(sample);

        // 모든 클라이언트에게 실시간으로 업데이트된 리스트 전송
        List<SampleResponseDTO> updatedSampleList = getSampleList();
        messagingTemplate.convertAndSend("/topic/samples", updatedSampleList);

    }
}
