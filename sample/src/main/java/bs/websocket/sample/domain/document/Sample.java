package bs.websocket.sample.domain.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sample")
@Builder
public class Sample {

    @Id
    private String id;
    private String name;
    private String description;

    private boolean isDelete = false;

    // 삭제 처리
    public void delete(){
        this.isDelete = true;
    }


}
