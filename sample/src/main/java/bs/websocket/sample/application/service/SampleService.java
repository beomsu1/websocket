package bs.websocket.sample.application.service;

import bs.websocket.sample.application.dto.SampleRequestDTO;
import bs.websocket.sample.application.dto.SampleResponseDTO;

import java.util.List;

public interface SampleService {

    // get List
    List<SampleResponseDTO> getSampleList();

    // Create
    void createSample(SampleRequestDTO sampleRequestDTO);

    // Read
    SampleResponseDTO getSampleById(String id);

    // Update
    void updateSample(String id, SampleRequestDTO sampleRequestDTO);

    // Delete
    void deleteSample(String id);
}
