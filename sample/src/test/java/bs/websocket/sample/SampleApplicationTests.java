package bs.websocket.sample;

import bs.websocket.sample.domain.repository.SampleRepository;
import bs.websocket.sample.domain.Sample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SampleApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private SampleRepository sampleRepository;

	@Test
	public void test1(){

		List<Sample> samples = sampleRepository.findAll();
		System.out.println("------------------------" + samples);
	}

}
