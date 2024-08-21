package bs.websocket.sample.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleRequestDTO {

    private String id;
    private String name;
    private String description;
}
