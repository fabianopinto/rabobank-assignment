package fabianopinto.waes.assignment.rabobank.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@MockEndpoints()
public class FileRouteBuilderTests {

	@EndpointInject(uri = "mock:file:OUTPUT")
	private MockEndpoint mockOutput;

	@EndpointInject(uri = "mock:file:REJECTED")
	private MockEndpoint mockRejected;

	@Autowired
	private ProducerTemplate producerTemplate;

	@Test
	public void testRejectedFile() throws Exception {
		mockOutput.expectedMessageCount(0);
		mockRejected.expectedMessageCount(1);
		producerTemplate.sendBodyAndHeader("file:INBOX", "", "CamelFileName", "invalid.txt");
		mockOutput.assertIsSatisfied();
		mockRejected.assertIsSatisfied();
	}

}
