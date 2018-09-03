package fabianopinto.waes.assignment.rabobank.beans;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fabianopinto.waes.assignment.rabobank.model.ProcessorResults;
import fabianopinto.waes.assignment.rabobank.model.StatementRecord;

public class ValidateStatementsBeanTest {

	@Test
	public void testValidate() {
		ValidateStatementsBean bean = new ValidateStatementsBean();
		ProcessorResults results = bean.validate(sampleStatementRecords());
		assertEquals(6, results.getResults().size());
		assertEquals(Integer.valueOf(1), results.getResults().get(0).getIndex());
		assertEquals(Integer.valueOf(2), results.getResults().get(1).getIndex());
		assertEquals(Integer.valueOf(4), results.getResults().get(2).getIndex());
		assertEquals(Integer.valueOf(4), results.getResults().get(3).getIndex());
		assertEquals(Integer.valueOf(6), results.getResults().get(4).getIndex());
		assertEquals(Integer.valueOf(7), results.getResults().get(5).getIndex());
		assertEquals(Integer.valueOf(2222), results.getResults().get(0).getReference());
		assertEquals(Integer.valueOf(3333), results.getResults().get(1).getReference());
		assertEquals(Integer.valueOf(2222), results.getResults().get(2).getReference());
		assertEquals(Integer.valueOf(2222), results.getResults().get(3).getReference());
		assertEquals(Integer.valueOf(7777), results.getResults().get(4).getReference());
		assertEquals(Integer.valueOf(2222), results.getResults().get(5).getReference());
		assertEquals("Reference not unique", results.getResults().get(0).getDescription());
		assertEquals("Incorrect end balance", results.getResults().get(1).getDescription());
		assertEquals("Reference not unique", results.getResults().get(2).getDescription());
		assertEquals("Incorrect end balance", results.getResults().get(3).getDescription());
		assertEquals("Incorrect end balance", results.getResults().get(4).getDescription());
		assertEquals("Reference not unique", results.getResults().get(5).getDescription());
	}

	private List<StatementRecord> sampleStatementRecords() {
		List<StatementRecord> records = new ArrayList<>();
		records.add(sampleStatementRecord(1111, 1000.01, 0, 1000.01));
		records.add(sampleStatementRecord(2222, 2000.02, -100.01, 1900.01));  // repeated reference
		records.add(sampleStatementRecord(3333, 3000.03, 100.01, 3100.44));   // wrong end balance
		records.add(sampleStatementRecord(4444, 4000.04, 0, 4000.04));
		records.add(sampleStatementRecord(2222, -5000.05, -100.01, 5100.06)); // repeated reference, wrong end balance
		records.add(sampleStatementRecord(6666, -6000.05, 100.01, -5900.04));
		records.add(sampleStatementRecord(7777, -7000.07, 0, -7000.77));      // wrong end balance
		records.add(sampleStatementRecord(2222, -8000.08, 100.01, -7900.07)); // repeated reference
		return records;
	}

	private StatementRecord sampleStatementRecord(int reference, double startBalance, double mutation, double endBalance) {
		StatementRecord record = new StatementRecord();
		record.setReference(reference);
		record.setStartBalance(BigDecimal.valueOf(startBalance));
		record.setMutation(BigDecimal.valueOf(mutation));
		record.setEndBalance(BigDecimal.valueOf(endBalance));
		return record;
	}

}
