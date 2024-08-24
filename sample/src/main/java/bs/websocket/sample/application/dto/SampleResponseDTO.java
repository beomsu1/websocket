package bs.websocket.sample.application.dto;

import bs.websocket.sample.domain.document.Sample;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleResponseDTO {

    private String id;
    private String name;
    private String description;

    public SampleResponseDTO(Sample sample) {

        this.id = sample.getId();
        this.name = sample.getName();
        this.description = sample.getDescription();
    }
}
