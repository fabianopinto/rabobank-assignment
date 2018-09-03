package fabianopinto.waes.assignment.rabobank.beans;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FileNameGeneratorBeanTest {

	@Test
	public void testResultsFileName() {
		FileNameGeneratorBean bean = new FileNameGeneratorBean();
		assertTrue(bean.resultsFileName("first.csv").matches("first-\\d{14}\\.csv"));
		assertTrue(bean.resultsFileName("second.xml").matches("second-\\d{14}\\.xml"));
	}

}
