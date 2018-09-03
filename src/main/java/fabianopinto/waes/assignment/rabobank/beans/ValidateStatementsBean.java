package fabianopinto.waes.assignment.rabobank.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fabianopinto.waes.assignment.rabobank.model.ProcessorResult;
import fabianopinto.waes.assignment.rabobank.model.ProcessorResults;
import fabianopinto.waes.assignment.rabobank.model.StatementRecord;

/**
 * Validation statement bean.
 * 
 * Validate each customer statement checking uniqueness of references and
 * correct end balance.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@Component
public class ValidateStatementsBean {

	private static final String NOT_UNIQUE = "Reference not unique";
	private static final String BALANCE_ERROR = "Incorrect end balance";

	public ProcessorResults validate(List<StatementRecord> records) {
		ProcessorResults results = new ProcessorResults();
		List<Integer> references = records.stream().map(record -> record.getReference()).collect(Collectors.toList());
		for (int index = 0; index < records.size(); index++) {
			StatementRecord record = records.get(index);
			if (!validateUnique(references, record)) {
				addResult(results, record.getReference(), NOT_UNIQUE, index);
			}
			if (!validateEndBalance(record)) {
				addResult(results, record.getReference(), BALANCE_ERROR, index);
			}
		}
		return results;
	}

	private boolean validateUnique(List<Integer> references, StatementRecord record) {
		return Collections.frequency(references, record.getReference()) == 1;
	}

	private boolean validateEndBalance(StatementRecord record) {
		try {
			return record.getStartBalance().add(record.getMutation()).equals(record.getEndBalance());
		} catch (NullPointerException e) {
			return false;
		}
	}

	private void addResult(ProcessorResults results, Integer reference, String description, Integer index) {
		ProcessorResult result = new ProcessorResult();
		result.setReference(reference);
		result.setDescription(description);
		result.setIndex(index);
		if (results.getResults() == null) {
			results.setResults(new ArrayList<>());
		}
		results.getResults().add(result);
	}

}
