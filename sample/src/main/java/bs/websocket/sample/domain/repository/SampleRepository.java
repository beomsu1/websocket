package bs.websocket.sample.domain.repository;

import bs.websocket.sample.domain.document.Sample;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SampleRepository extends MongoRepository<Sample, String> {

    List<Sample> findAllByIsDeleteFalse();
}
